package com.example.reviewservice.controller;

import com.example.reviewservice.bussines.ReviewService;
import com.example.reviewservice.domain.Review;
import com.example.reviewservice.excepiton.InvalidData;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ReviewController {
    private ReviewService service;

    @PostMapping()
    public ResponseEntity<Review> saveUser(@RequestBody Review request) {
        try{

            return ResponseEntity.ok().body(service.saveReview(request));
        }
        catch (InvalidData err){
            throw err;
        }

    }

    @PostMapping("/driverReviews/{driverId}")
    public ResponseEntity<List<Review>> getReviewsForDriver(@PathVariable(value = "driverId") Long driverId){
        return ResponseEntity.ok().body(service.filterByDriverId(driverId));
    }

    @PostMapping("/driveeReviews/{userId}")
    public ResponseEntity<List<Review>> getReviewsForDrivee(@PathVariable(value = "driverId") Long userId){
        return ResponseEntity.ok().body(service.filterByUserId(userId));
    }

    @GetMapping("/{driverId}")
    public int getAvgRating(@PathVariable(value = "driverId") Long driverId)throws InvalidData{
        try {

            return service.getAverageRating(driverId);
        }
        catch (InvalidData err){
            throw err;
        }
    }
    


}
