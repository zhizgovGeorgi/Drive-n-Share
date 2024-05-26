package com.example.travelservice.bussines;

import com.example.travelservice.bussines.impl.TravelServiceImpl;
import com.example.travelservice.model.Travel;
import com.example.travelservice.persistence.TravelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelServiceTest {
    @Mock
    private TravelRepository repository;

    @InjectMocks
    private TravelServiceImpl service;



    //HAPPY FLOW
    @Test
    void testCreateTravel_shouldReturnCreatedTravel(){
        //ARRANGE
        Travel travel = Travel.builder().startPoint("A").endPoint("BB").driverId(1L).departureDate("01-02-2025").pricePerPerson(22.4).build();
        when(repository.save(any(Travel.class))).thenReturn(travel);

        //ACT
        Travel result = service.save(travel);

        //ASSERT
        assertEquals(travel.getId(), result.getId());
        assertEquals(travel.getStartPoint(), result.getStartPoint());
        assertEquals(travel.getEndPoint(), result.getEndPoint());
        verify(repository, times(1)).save(any(Travel.class));
    }

    //HAPPY FLOW
    @Test
    void findByEndPoint_shouldReturnCorrectList(){
        //ARRANGE
        Travel travel = Travel.builder().startPoint("A").endPoint("BB").driverId(1L).departureDate("01-02-2025").pricePerPerson(22.4).build();
//        Travel travel2 = Travel.builder().startPoint("D").endPoint("C").driverId(1L).departureDate("08-02-2025").pricePerPerson(22.4).build();
        when(repository.findByEndPoint(any(String.class))).thenReturn(List.of(travel));

        //ACT
        List<Travel> results = service.findByEndPoint("BB");

        //ASSERT
        assertEquals(travel.getStartPoint(), results.get(0).getStartPoint());
        assertEquals(travel.getPricePerPerson(), results.get(0).getPricePerPerson());
        assertEquals(travel.getDriverId(), results.get(0).getDriverId());
        verify(repository, times(2)).findByEndPoint(any(String.class));
    }
}
