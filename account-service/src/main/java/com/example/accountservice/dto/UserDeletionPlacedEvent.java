package com.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UserDeletionPlacedEvent {
    public UserDeletionPlacedEvent(){

    }
    private Long driverId;
    private String email;
}
