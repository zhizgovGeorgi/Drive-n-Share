package com.example.reviewservice.bussines;

import com.example.reviewservice.excepiton.InvalidData;
import com.example.reviewservice.domain.Review;

import java.util.List;

public interface ReviewService {
     Review saveReview(Review request) throws InvalidData;
     List<Review> filterByUserId(Long userId);
     List<Review>filterByDriverId(Long driverId);

     int getAverageRating(Long driverId);
}
