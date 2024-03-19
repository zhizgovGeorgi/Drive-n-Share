package com.example.travelservice.converter;

import com.example.travelservice.dto.CreateTravelRequest;
import com.example.travelservice.dto.TravelResponse;
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
    public static TravelResponse travelToResponse(Travel travel){
        TravelResponse response = TravelResponse.builder()
                .startPoint(travel.getStartPoint())
                .endPoint(travel.getEndPoint())
                .departureDate(travel.getDepartureDate())
                .pricePerPerson(travel.getPricePerPerson())
                .driverId(travel.getDriverId())
                .build();
        return response;
    }
}
