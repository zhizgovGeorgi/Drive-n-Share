package com.example.travelservice.bussines.impl;

import com.example.travelservice.bussines.TravelService;
import com.example.travelservice.model.Travel;
import com.example.travelservice.persistence.TravelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TravelServiceImpl implements TravelService {
    private TravelRepository repository;
    @Override
    public Travel save(Travel travel) {
        return repository.save(travel);
    }

    @Override
    public List<Travel>findByEndPoint(String endPoint) {
        if(repository.findByEndPoint(endPoint) == null ) {
            return null;
        }
        else {
            return repository.findByEndPoint(endPoint);
        }
    }
    @Override
    public List<Travel>findByDriver(Long driverId) {
        if(repository.findByDriverId(driverId) == null ) {
            return null;
        }
        else {
            return repository.findByDriverId(driverId);
        }
    }


    @Override
    public List<Travel> findByStartPoint(String startPoint) {
        if(repository.findByStartPoint(startPoint) == null ) {
            return null;
        }
        else {
            return repository.findByStartPoint(startPoint);
        }
    }

    @Override
    public List<Travel> findByStartAndEndPoint(String startPoint, String endPoint) {
        if(repository.findByStartPointAndEndPoint(startPoint, endPoint) == null ) {
            return null;
        }
        else {
            return repository.findByStartPointAndEndPoint(startPoint, endPoint);
        }
    }

    @Override
    public List<Travel> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (repository. findById(id) != null){
            repository.deleteById(id);
        }
        else {
            throw new Exception("You cannot delete this travel");
        }
    }
}
