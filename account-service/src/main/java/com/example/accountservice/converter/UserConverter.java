package com.example.accountservice.converter;

import com.example.accountservice.domain.User;
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
}
