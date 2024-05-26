package com.example.travelservice.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
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
