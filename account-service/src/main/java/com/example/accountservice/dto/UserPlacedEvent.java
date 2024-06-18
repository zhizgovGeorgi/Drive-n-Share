package com.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserPlacedEvent {
    public UserPlacedEvent(){

    }
    private Long id;
    private String email;
    private String password;
}
