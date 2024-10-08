package com.example.travelservice.model;

import com.example.travelservice.exception.InvalidData;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String startPoint;
    @NonNull
    private String endPoint;
    @NonNull
    private double pricePerPerson;
    @NonNull
    private Long driverId;
    @NonNull
    private String departureDate;

    public void setStartDate(String startPoint)throws IllegalArgumentException {
        int MAX_LENGTH = 256;
        if (startPoint.length() <= MAX_LENGTH) {
            this.startPoint = startPoint;
        } else {
            throw new IllegalArgumentException("Input is too long");
        }
    }

public void setEndPoint(String endPoint)throws InvalidData{
    if(endPoint == this.startPoint){
        throw new InvalidData();
    }
    else{
        this.endPoint = endPoint;
        this.setStartPoint("starrt");
    }
}
}
