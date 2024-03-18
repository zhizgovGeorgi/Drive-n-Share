package com.example.travelservice.controller;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter


public class CreateTravelRequest {
    @NonNull
    private String startPoint;
    @NonNull
    private String endPoint;
    @NonNull
    private double pricePerPerson;

    private Long driverId;
    @NonNull
    private String departureDate;
}
