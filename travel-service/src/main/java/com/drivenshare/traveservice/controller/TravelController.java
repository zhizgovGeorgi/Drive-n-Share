package com.drivenshare.traveservice.controller;


import com.drivenshare.traveservice.model.Travel;
import com.drivenshare.traveservice.service.TravelService;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.DateFormatter;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travels")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class TravelController {
    private final TravelService service;


    @GetMapping()
    public ResponseEntity<List<Travel>> getTravels() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Travel> saveTravel(@RequestBody Travel request) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/travels").toUriString());
        return ResponseEntity.created(uri).body(service.save(request));
    }


}