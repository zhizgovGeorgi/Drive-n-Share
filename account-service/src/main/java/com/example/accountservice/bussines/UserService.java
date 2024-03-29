package com.example.accountservice.bussines;

import com.example.accountservice.domain.User;
import com.example.accountservice.dto.UserRequest;
import com.example.accountservice.exception.InvalidData;

import java.util.List;

public interface UserService {
    User save(User request)throws InvalidData;
    List<User> findUserByRole(String role)throws InvalidData;
}
