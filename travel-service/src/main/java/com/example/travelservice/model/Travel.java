package com.example.travelservice.model;

import com.example.travelservice.exception.InvalidData;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private Long driverId;
    @NonNull
    private String departureDate;


public void setEndPoint(String endPoint)throws InvalidData{
    if(endPoint == this.startPoint){
        this.endPoint = endPoint;
    }
    else{
        throw new InvalidData();
    }
}
}
