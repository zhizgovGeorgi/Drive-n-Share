package com.example.travelservice.controller;

import com.example.travelservice.bussines.TravelService;
import com.example.travelservice.converter.TravelConverter;
import com.example.travelservice.dto.CreateTravelRequest;
import com.example.travelservice.dto.TravelResponse;
import com.example.travelservice.model.Travel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/travels")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class TravelController {
    private final TravelService service;

    @GetMapping()
    public ResponseEntity<List<Travel>> getTravels(){
//        List<Travel> travels =service.findAll();
//        return travels.stream().map(this::TravelConverter.travelToResponse()).toList();
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping()
    public TravelResponse save(@RequestBody  CreateTravelRequest request){
        Travel travel = TravelConverter.requestToTravel(request);
        TravelResponse response = TravelConverter.travelToResponse(service.save(travel));
        return response;
    }
}
