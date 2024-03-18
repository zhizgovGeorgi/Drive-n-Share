package com.example.travelservice.model;

import lombok.*;

//@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Travel {
//    @Id
    private Long id;
    @NonNull
    private String startPoint;
    @NonNull
    private String endPoint;
    @NonNull
    private double pricePerPerson;

    private Long userId;
    @NonNull
    private String departureDate;
}
