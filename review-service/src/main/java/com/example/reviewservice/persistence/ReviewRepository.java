package com.example.reviewservice.persistence;

import com.example.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review save(Review request);
    @Query(value =
            "select avg('rating') FROM 'review' where 'travel_id'=:travelId ", nativeQuery = true)
    int averageRating(@Param("travelId")int travelId);
    List<Review> findByUserId(Long userId);
    List<Review> findByDriverId(Long driverId);
}
