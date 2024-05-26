package com.example.travelservice.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class FindTravelRequest {
    private String startPoint;
    private String endPoint;
}
