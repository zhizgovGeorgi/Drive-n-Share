package com.example.accountservice.bussines.impl;

import com.example.accountservice.bussines.UserService;
import com.example.accountservice.domain.User;
import com.example.accountservice.dto.UserPlacedEvent;
import com.example.accountservice.dto.UserRequest;
import com.example.accountservice.exception.InvalidData;
import com.example.accountservice.persistence.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private final KafkaTemplate<String, UserPlacedEvent> kafkaTemplate;

    @Override
    public User save(User request) throws InvalidData {
        if (repository.existsByEmail(request.getEmail()) == false) {
            User newUser = repository.save(request);
            kafkaTemplate.send("newUserCreation", new UserPlacedEvent(newUser.getId(), newUser.getEmail(), newUser.getPassword(), newUser.getRole()));
            return newUser;
        }
        else {
            throw new InvalidData();
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
}
