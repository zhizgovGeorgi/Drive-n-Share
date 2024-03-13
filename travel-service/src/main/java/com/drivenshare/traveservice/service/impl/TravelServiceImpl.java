package com.drivenshare.traveservice.service.impl;

import com.drivenshare.traveservice.model.Travel;
import com.drivenshare.traveservice.service.TravelService;
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
    public List<Travel> findAll() {
        return null;
    }
}
