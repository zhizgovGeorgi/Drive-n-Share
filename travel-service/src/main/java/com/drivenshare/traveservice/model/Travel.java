package com.drivenshare.traveservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Travel {
    @Id
    private String id;
    private String startPoint;
    private String endPoint;
    private double pricePerPerson;

}
