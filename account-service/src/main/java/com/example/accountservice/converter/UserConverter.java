package com.example.accountservice.converter;

import com.example.accountservice.domain.User;
import com.example.accountservice.dto.UpdateUserRequest;
import com.example.accountservice.dto.UserRequest;
import com.example.accountservice.dto.UserResponse;

public class UserConverter {
    public static User requestToEntity(UserRequest request) {
        User user = User.builder()
                .fName(request.getFirstName())
                .lName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .adress(request.getAdress())
                .role(request.getRole())
                .build();
        return user;
    }

    public static UserResponse entityToResponse(User user) {
        UserResponse response = UserResponse.builder()
                .id(user.getId())
                .fName(user.getFName())
                .lName(user.getLName())
                .email(user.getEmail())
                .adress(user.getAdress())
                .build();
        return response;
    }
    public static User updateRequestToUser(User user, UpdateUserRequest request) {
        user.setFName(request.getFirstName());
        user.setLName(request.getLastName());
        user.setAdress(request.getAdress());
        return user;
    }
}
