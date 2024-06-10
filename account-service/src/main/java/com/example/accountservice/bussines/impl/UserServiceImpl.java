package com.example.accountservice.bussines.impl;

import com.example.accountservice.bussines.UserService;
import com.example.accountservice.domain.User;
import com.example.accountservice.dto.UserDeletionPlacedEvent;
import com.example.accountservice.dto.UserPlacedEvent;
import com.example.accountservice.exception.DuplicationException;
import com.example.accountservice.exception.InvalidData;
import com.example.accountservice.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private final PasswordEncoder encoder;
    private final KafkaTemplate<String, UserPlacedEvent> kafkaTemplate;
    private final KafkaTemplate<String, UserDeletionPlacedEvent> kafkaDeletionTemplate;

    @Override
    public User save(User request) throws DuplicationException {
        if (repository.existsByEmail(request.getEmail()) == false) {
            request.setPassword(encoder.encode(request.getPassword()));
            User newUser = repository.save(request);
            //sends the user to the authentication service, so that it will add it in the auth db
            kafkaTemplate.send("newUserCreation", new UserPlacedEvent(newUser.getId(), newUser.getEmail(), newUser.getPassword(), newUser.getRole()));
            return newUser;
        }
        else {
            throw new DuplicationException();
        }
    }



    @Override
    public List<User> findUserByRole(String role)throws InvalidData {
        if (role != ""){
            return repository.findUserByRole(role);
        }
        else{
            throw new InvalidData();
        }
    }

    @Override
    public Optional<User> findById(Long id) throws InvalidData {
       if(id == null){
           throw new InvalidData();
       }
       return repository.findById(id);
    }

    @Override
    public void deleteUser(Long id, Long userId) {
        Optional<User> userForDeletion = repository.findById(id);
        Optional<User>deletingUser = repository.findById(userId);
        if (!userForDeletion.equals(deletingUser)){
            throw new IllegalArgumentException("You do not have the right to delete this account");
        }
        kafkaDeletionTemplate.send("userDeletion", new UserDeletionPlacedEvent(id.longValue()));
        repository.deleteById(id);
        //sends it to the rest of the services, where everything related to the user will be deleted

    }

    @Override
    public User updateUser(User updateRequest) {
        updateRequest.setPassword(encoder.encode(updateRequest.getPassword()));
        return repository.save(updateRequest);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
