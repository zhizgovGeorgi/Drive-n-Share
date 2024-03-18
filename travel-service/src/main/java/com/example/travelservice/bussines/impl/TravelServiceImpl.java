package com.example.travelservice.bussines.impl;

import com.example.travelservice.bussines.TravelService;
import com.example.travelservice.model.Travel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TravelServiceImpl implements TravelService {
    @Override
    public Travel save(Travel travel) {
        return null;
    }

    @Override
    public Travel findByEndPoint(Travel travel) {
        return null;
    }

    @Override
    public Travel findByStartPoint(Travel travel) {
        return null;
    }

    @Override
    public List<Travel> findAll() {
        return null;
    }
}
