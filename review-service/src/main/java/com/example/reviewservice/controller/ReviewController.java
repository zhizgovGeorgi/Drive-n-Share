package com.example.reviewservice.controller;

import com.example.reviewservice.bussines.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ReviewController {
    private ReviewService service;


}
