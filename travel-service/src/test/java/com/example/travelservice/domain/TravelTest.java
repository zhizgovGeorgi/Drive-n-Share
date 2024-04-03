package com.example.travelservice.domain;

import com.example.travelservice.exception.InvalidData;
import com.example.travelservice.model.Travel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TravelTest {
    //happy flow
    @Test
    void travelTestConstructor(){
        //arrange
        Travel actualTravel = new Travel();
        //act
        actualTravel.setDepartureDate("12-12-2025");
        actualTravel.setStartPoint("Eindhoven");
        actualTravel.setEndPoint("Roermond");
        actualTravel.setId(123L);
        actualTravel.setPricePerPerson(10.0d);
        actualTravel.setDriverId(123L);
        //assert
        assertEquals("Eindhoven", actualTravel.getStartPoint());
        assertEquals("Roermond", actualTravel.getEndPoint());
        assertEquals(123L, actualTravel.getId().longValue());
        assertEquals(10.0d, actualTravel.getPricePerPerson());
    }

//    //happy flow
//    @Test
//    void testSetEndPoint() throws InvalidData {
//        //arrange
//        Travel travel = new Travel();
//        //act
//        travel.setStartPoint("A");
////        travel.setEndPoint("A");
//        //assert
//        assertThrows(InvalidData.class, () -> travel.setEndPoint("A"));
//    }


}
