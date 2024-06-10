package com.example.travelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDeletionPlacedEvent {
    public UserDeletionPlacedEvent(){

    }
    private Long driverId;
}
