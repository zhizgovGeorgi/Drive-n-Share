package com.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponse {
    private String adminEmail;
    private String driveeEmail;
    private String respond;
}
