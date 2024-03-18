package com.example.travelservice.controller;

import com.example.travelservice.bussines.TravelService;
import com.example.travelservice.converter.TravelConverter;
import com.example.travelservice.model.Travel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travels")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class TravelController {
    private final TravelService service;
    private final TravelConverter converter;

    @GetMapping()
    public ResponseEntity<List<Travel>> getTravels(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping()
    public Travel save(CreateTravelRequest request){
        Travel travel = converter.requestToTravel(request);
        return travel;
    }
}
