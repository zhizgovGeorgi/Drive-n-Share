package com.example.travelservice.persistence;

import com.example.travelservice.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    Travel save(Travel travel);
    List<Travel>findAll();
    Optional<Travel> findById(Long id);
    List<Travel> findByDriverId(Long driverId);
    List<Travel> findByStartPointAndEndPoint(String startPoint, String endPoint);
    List<Travel> findByStartPoint(String startPoint);
    List<Travel> findByEndPoint(String endPoint);
}
