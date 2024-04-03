package com.example.travelservice.bussines;

import com.example.travelservice.model.Travel;

import java.util.List;

public interface TravelService {
    Travel save(Travel travel);
    Travel findByEndPoint(Travel travel);
    Travel findByStartPoint(Travel travel);
    List<Travel> findAll();
    void deleteById(Long id) throws Exception;
}
