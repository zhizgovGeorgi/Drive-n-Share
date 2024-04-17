package com.example.travelservice;

import com.example.travelservice.dto.UserPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class TravelServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelServiceApplication.class, args);
    }

    @KafkaListener(topics = "newUserCreation")
    public void handleNewUser(UserPlacedEvent userPlacedEvent){
    log.info("New user with email : {}", userPlacedEvent.getEmail());
    }

}
