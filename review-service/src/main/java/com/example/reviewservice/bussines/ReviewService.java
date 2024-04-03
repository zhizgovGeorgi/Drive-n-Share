package com.example.reviewservice.bussines;

import com.example.reviewservice.bussines.impl.ReviewServiceImpl;
import com.example.reviewservice.excepiton.InvalidData;
import com.example.reviewservice.model.Review;

import java.util.List;

public interface ReviewService {
     Review saveReview(Review request) throws InvalidData;
     List<Review> filterByUserId(Long userId);
     List<Review>filterByDriverId(Long driverId);
}
