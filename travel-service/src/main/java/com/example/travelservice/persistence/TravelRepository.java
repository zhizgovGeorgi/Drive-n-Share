package com.example.travelservice.persistence;

import com.example.travelservice.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    Travel save(Travel travel);
    List<Travel>findAll();
}
