package com.example.reviewservice.persistence;

import com.example.reviewservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review save(Review request);
    @Query(value =
            "SELECT driverId, AVG(rating) AS average_rating FROM review GROUP BY driverId", nativeQuery = true)
    int averageRating(@Param("driverId")Long driverId);
    List<Review> findByUserId(Long userId);
    List<Review> findByDriverId(Long driverId);
}
