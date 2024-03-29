package com.example.accountservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String fName;
    private String lName;
    private String email;
    private String role;
    private String adress;
    private String password;
}
