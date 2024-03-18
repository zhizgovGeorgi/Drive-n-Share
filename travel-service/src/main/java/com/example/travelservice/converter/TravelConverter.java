package com.example.travelservice.converter;

import com.example.travelservice.controller.CreateTravelRequest;
import com.example.travelservice.model.Travel;

public class TravelConverter {
    public static Travel requestToTravel(CreateTravelRequest request){
        Travel travel = Travel.builder()
                .startPoint(request.getStartPoint())
                .endPoint(request.getEndPoint())
                .departureDate(request.getDepartureDate())
                .pricePerPerson(request.getPricePerPerson())
                .driverId(request.getDriverId())
                .build();
        return travel;
    }
}
