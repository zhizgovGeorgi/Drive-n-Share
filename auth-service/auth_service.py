import requests
import time
from kafka import KafkaConsumer
import threading
import json

class AuthService:

    def __init__(self):
        self.keycloak_root = "http://localhost:8282"
        self.realm = 'dns-realm'
        self.admin_user = 'admin'
        self.admin_password = 'admin'
        self.access_token = None
        self.token_expiry = 0
        self.client_id = 'dns-client'
        self.kafka_topic = 'newUserCreation'
        self.kafka_topic2 = 'userDeletion'
        self.kafka_bootstrap_servers = ['localhost:9092']
        self.start_kafka_consumer()
        # self.consumer = KafkaConsumer(
        #     'newUserCreation',
        #     bootstrap_servers=['localhost:9092'],
        #     auto_offset_reset='earliest',
        #     enable_auto_commit=True,
        #     group_id='your_group_id',
        #     value_deserializer=lambda x: x.decode('utf-8')
        # )

    def get_access_token(self):
        if self.access_token and time.time() < self.token_expiry:
            return self.access_token

        resp = requests.post(
            f"{self.keycloak_root}/realms/master/protocol/openid-connect/token",
            data={
                "client_id": "admin-cli",
                "username": self.admin_user,
                "password": self.admin_password,
                "grant_type": "password"
            }
        )
        resp.raise_for_status()
        res = resp.json()
        self.access_token = res["access_token"]
        self.token_expiry = time.time() + res["expires_in"] - 60
        return self.access_token


    def start_kafka_consumer(self):
        def kafka_consumer_thread():
            consumer = KafkaConsumer(
                self.kafka_topic,
                bootstrap_servers=self.kafka_bootstrap_servers,
                auto_offset_reset='earliest',
                enable_auto_commit=True,
                group_id='auth_service_group',
                value_deserializer=lambda x: json.loads(x.decode('utf-8'))
            )
            consumer2 = KafkaConsumer(
                self.kafka_topic2,
                bootstrap_servers=self.kafka_bootstrap_servers,
                auto_offset_reset='earliest',
                enable_auto_commit=True,
                group_id='auth_service_group',
                value_deserializer=lambda x: json.loads(x.decode('utf-8'))
            )
            for message in consumer:
                user_data = message.value
                self.create_user(user_data)
            for message in consumer2:
                user_data = message.value
                self.delete_user(user_data)

        thread = threading.Thread(target=kafka_consumer_thread)
        thread.daemon = True
        thread.start()


    def get_auth_headers(self):
        token = self.get_access_token()
        return {"Authorization": f"Bearer {token}"}

    def create_user(self, user_data):
        email = user_data['email']
        id = user_data['id']
        username = user_data['email']
        password = user_data['password']

        resp = requests.get(
            f"{self.keycloak_root}/admin/realms/{self.realm}/users",
            headers=self.get_auth_headers(),
            params={"username": username}
        )
        resp.raise_for_status()
        users = resp.json()

        if not any(user['username'] == username for user in users):
            user_settings = {
                "email": email,
                "username": username,
                "enabled": True,
                "credentials": [{
                    "type": "password",
                    "value": password,
                    "temporary": False,
                }]
            }

            resp = requests.post(
                f"{self.keycloak_root}/admin/realms/{self.realm}/users",
                json=user_settings,
                headers=self.get_auth_headers(),
            )
            resp.raise_for_status()
            location = resp.headers["Location"]
            user_id = location.split('/')[-1]  # Extract user ID from the location header
            print(f"User created at {location}")
            # Automatically assign the "user" role
            self.assign_client_role_to_user(user_id, "Drivee")
            return "User successfully created"
        else:
            print(f"User '{username}' already exists")

    def delete_user(self, username, auth_token):
        resp = requests.get(
            f"{self.keycloak_root}/admin/realms/{self.realm}/users",
            headers={"Authorization": f"Bearer {auth_token}"},
            params={"username": username}
        )
        resp.raise_for_status()
        users = resp.json()

        user = next((user for user in users if user['username'] == username), None)
        if user:
            user_id = user['id']
            resp = requests.delete(
                f"{self.keycloak_root}/admin/realms/{self.realm}/users/{user_id}",
                headers={"Authorization": f"Bearer {auth_token}"},
            )
            resp.raise_for_status()
            print(f"User '{username}' deleted")
        else:
            print(f"User '{username}' not found")

    def login_user(self, username, password):
        resp = requests.post(
            f"{self.keycloak_root}/realms/{self.realm}/protocol/openid-connect/token",
            data={
                "client_id": self.client_id,
                "username": username,
                "password": password,
                "grant_type": "password"
            }
        )
        resp.raise_for_status()
        res = resp.json()
        print(f"User successfully logged in")
        return res

    def update_user(self, user_id, update_data, auth_token):
        resp = requests.put(
            f"{self.keycloak_root}/admin/realms/{self.realm}/users/{user_id}",
            headers={"Authorization": f"Bearer {auth_token}"},
            json=update_data
        )
        resp.raise_for_status()
        print(f"User '{user_id}' updated")
        return "User successfully updated"

    def assign_client_role_to_user(self, user_id, role_name):
        # Get client ID by client name
        resp = requests.get(
            f"{self.keycloak_root}/admin/realms/{self.realm}/clients",
            headers=self.get_auth_headers(),
            params={"clientId": self.client_id}
        )
        resp.raise_for_status()
        clients = resp.json()
        client = next((c for c in clients if c['clientId'] == self.client_id), None)
        if not client:
            print(f"Client '{self.client_id}' not found")
            return

        client_id = client['id']

        # Get role ID by role name
        resp = requests.get(
            f"{self.keycloak_root}/admin/realms/{self.realm}/clients/{client_id}/roles",
            headers=self.get_auth_headers(),
        )
        resp.raise_for_status()
        roles = resp.json()
        role = next((r for r in roles if r['name'] == role_name), None)
        if not role:
            print(f"Role '{role_name}' not found")
            return

        role_id = role['id']

        # Assign role to user
        resp = requests.post(
            f"{self.keycloak_root}/admin/realms/{self.realm}/users/{user_id}/role-mappings/clients/{client_id}",
            headers=self.get_auth_headers(),
            json=[{"id": role_id, "name": role_name}]
        )
        resp.raise_for_status()
        print(f"Client role '{role_name}' assigned to user '{user_id}'")

