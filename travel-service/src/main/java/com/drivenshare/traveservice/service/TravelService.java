package com.drivenshare.traveservice.service;


import com.drivenshare.traveservice.model.Travel;

import java.util.List;

public interface TravelService {
    Travel save(Travel travel);
    List<Travel>findAll();
}
