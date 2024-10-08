package com.example.accountservice.bussines;

import com.example.accountservice.domain.User;
import com.example.accountservice.dto.RequestResponse;
import com.example.accountservice.exception.DuplicationException;
import com.example.accountservice.exception.InvalidData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User request)throws DuplicationException;
    List<User> findUserByRole(String role)throws InvalidData;

    Optional<User> findById(Long id);
    void deleteUser(Long id, Long userId);

    User updateUser(User updateRequest);
    User findByEmail(String email);
    String respondToDriversRequest(RequestResponse requestResponse);
    String requestDriversAccount(MultipartFile file , String email) throws IOException;

}
