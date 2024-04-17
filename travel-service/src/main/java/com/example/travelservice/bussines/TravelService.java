package com.example.travelservice.bussines;

import com.example.travelservice.model.Travel;

import java.util.List;
import java.util.Optional;

public interface TravelService {
    Travel save(Travel travel);
    List<Travel> findByEndPoint(String endPoint);
    List<Travel> findByStartPoint(String startPoint);
    List<Travel> findAll();
    List<Travel>findByDriver(Long driverId);
    List<Travel> findByStartAndEndPoint(String startPoint, String endPoint);
    void deleteById(Long id) throws Exception;
}
