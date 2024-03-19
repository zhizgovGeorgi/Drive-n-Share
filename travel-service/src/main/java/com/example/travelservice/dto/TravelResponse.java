package com.example.travelservice.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelResponse {
    private String startPoint;
    private String endPoint;
    private double pricePerPerson;
    private Long driverId;
    private String departureDate;
}
