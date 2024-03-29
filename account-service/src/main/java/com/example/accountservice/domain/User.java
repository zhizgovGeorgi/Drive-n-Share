package com.example.accountservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String fName;
    @NonNull
    private String lName;
    @NonNull
    private String adress;
    private String email;
    @NonNull
    private String role;
    @NonNull
    private String password;
}
