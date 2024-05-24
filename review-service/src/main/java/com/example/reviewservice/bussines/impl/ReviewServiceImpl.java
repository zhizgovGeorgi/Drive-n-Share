package com.example.reviewservice.bussines.impl;

import com.example.reviewservice.bussines.ReviewService;
import com.example.reviewservice.excepiton.InvalidData;
import com.example.reviewservice.domain.Review;
import com.example.reviewservice.persistence.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository repository;

    public Review saveReview(Review request) throws InvalidData{
        if (request.getReview() != ""){
            return repository.save(request);
        }
        else{
            throw new InvalidData();
        }
    }

    public List<Review>filterByUserId(Long userId){
        return repository.findByUserId(userId);
    }

    public List<Review>filterByDriverId(Long driverId){
        return repository.findByDriverId(driverId);
    }

    public int getAverageRating(Long driverId){
        if(!driverId.equals(null)) {
            return repository.averageRating(driverId);
        }
        else {
            return 0;
        }
    }
}
