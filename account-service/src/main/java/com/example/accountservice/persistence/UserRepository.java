package com.example.accountservice.persistence;

import com.example.accountservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User request);
    boolean existsByEmail(String email);
    List<User> findUserByRole(String role);
    Optional<User> findById(Long id);
    void deleteById(Long id);
    User findByEmail(String email);
    User findUserById(long id);
}
