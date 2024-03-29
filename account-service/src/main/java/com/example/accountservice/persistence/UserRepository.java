package com.example.accountservice.persistence;

import com.example.accountservice.domain.User;
import com.example.accountservice.dto.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User request);
    boolean existsByEmail(String email);
    List<User> findUserByRole(String role);
}
