package com.example.accountservice.controller;

import com.example.accountservice.bussines.UserService;
import com.example.accountservice.converter.UserConverter;
import com.example.accountservice.domain.User;
import com.example.accountservice.dto.UserRequest;
import com.example.accountservice.dto.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest request) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
        User user = UserConverter.requestToEntity(request);
        UserResponse response = UserConverter.entityToResponse(service.save(user));
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllTravelRequests(String role){
//        return ResponseEntity.ok().body(service.findAll());
        List<User> drivers = service.findUserByRole(role);
        List<UserResponse> responses = drivers.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok().body(responses);

    }

    private UserResponse mapToResponse(User user){
        UserResponse response = UserConverter.entityToResponse(user);
        return response;
    }

}
