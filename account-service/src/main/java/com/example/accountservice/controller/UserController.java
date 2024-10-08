package com.example.accountservice.controller;

import com.example.accountservice.bussines.UserService;
import com.example.accountservice.converter.UserConverter;
import com.example.accountservice.domain.User;
import com.example.accountservice.dto.*;
import com.example.accountservice.exception.DuplicationException;
import com.example.accountservice.exception.InvalidData;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody UserRequest request) throws DuplicationException {
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
            User user = UserConverter.requestToEntity(request);
            UserResponse response = UserConverter.entityToResponse(service.save(user));
//            return ResponseEntity.created(uri).body(response);
            return ResponseEntity.created(uri).body("User has been created successfully");
        }
        catch (DuplicationException err){
            throw err;
        }

    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllDriverRequests(String role){
//        return ResponseEntity.ok().body(service.findAll());
        List<User> drivers = service.findUserByRole(role);
        List<UserResponse> responses = drivers.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok().body(responses);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteAccount(@RequestBody DeleteUserRequest request){
        try{
            service.deleteUser(request.getId(), request.getUserId());
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.ok().body("User has been deleted successfully");
    }

    @PostMapping("/requestDriversAccount")
    public ResponseEntity<String> requestDriversAccount(@RequestParam("file") MultipartFile file, @RequestParam("email") String email)
            throws IOException {

        String fileName = service.requestDriversAccount(file, email);
        return ResponseEntity.ok(fileName);
    }

    @PostMapping("/respondToRequest")
    public ResponseEntity<String> requestDriversAccount(@RequestBody RequestResponse requestResponse)
    {

        return ResponseEntity.ok("The new status is " +  service.respondToDriversRequest(requestResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "id") Long id)throws InvalidData{
        try {
            Optional<User> user = service.findById(id);
            return ResponseEntity.ok().body(user);
        }
        catch (InvalidData err){
            throw err;
        }
    }

    @PostMapping("/profile")
    public ResponseEntity<User> getUserByEmail(@RequestParam(value = "email") String email)throws InvalidData{
        try {
            User user = service.findByEmail(email);
            return ResponseEntity.ok().body(user);
        }
        catch (InvalidData err){
            throw err;
        }
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest request)throws InvalidData{
        try{
            User currUser = service.findByEmail(request.getEmail());
            User updatedUser = UserConverter.updateRequestToUser(currUser, request);

            return ResponseEntity.ok().body(UserConverter.entityToResponse( service.updateUser(updatedUser)));

        }
        catch (InvalidData err){
            throw err;
        }
    }

    private UserResponse mapToResponse(User user){
        UserResponse response = UserConverter.entityToResponse(user);
        return response;
    }

}
