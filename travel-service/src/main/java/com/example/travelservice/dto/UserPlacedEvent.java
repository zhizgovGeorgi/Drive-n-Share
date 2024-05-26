package com.example.travelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UserPlacedEvent {
    private Long id;
    private String email;
    private String password;
    private String role;
}
