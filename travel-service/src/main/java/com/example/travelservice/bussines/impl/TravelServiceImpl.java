package com.example.travelservice.bussines.impl;

import com.example.travelservice.bussines.TravelService;
import com.example.travelservice.model.Travel;
import com.example.travelservice.persistence.TravelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Travel findByEndPoint(Travel travel) {
        return null;
    }

    @Override
    public Travel findByStartPoint(Travel travel) {
        return null;
    }

    @Override
    public List<Travel> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (repository.findById(id) != null){
            repository.deleteById(id);
        }
        else {
            throw new Exception("You cannot delete this travel");
        }
    }
}
