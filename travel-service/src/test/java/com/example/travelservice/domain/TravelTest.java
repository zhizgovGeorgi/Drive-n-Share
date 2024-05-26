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

    //unhappy flow
    @Test
    void travelTestConstructorError(){
        //arrange
        Travel actualTravel = Travel.builder().startPoint("A").endPoint("BB").driverId(1L).departureDate("01-02-2025").pricePerPerson(22.4).build();
        //assert
        assertThrows(InvalidData.class, () ->  actualTravel.setEndPoint("A"));
    }
    @Test
    void travelTestConstructorNullErrorForEndPoint(){

        //assert
        assertThrows(NullPointerException.class, () -> Travel.builder().startPoint("A").driverId(1L).departureDate("01-02-2025").pricePerPerson(22.4).build());
    }

    @Test
    void travelTestConstructorNullErrorForStartPoint(){

        //assert
        assertThrows(NullPointerException.class, () -> Travel.builder().endPoint("A").driverId(1L).departureDate("01-02-2025").pricePerPerson(22.4).build());
    }

    @Test
    void travelTestConstructorNoNullErrorForDriverId(){
        assertThrows(NullPointerException.class, () -> Travel.builder().endPoint("A").startPoint("Y").startPoint("b").pricePerPerson(22.4).build());

    }

    @Test
    void travelTestConstructorNullErrorForDepartureDate(){

        //assert
        assertThrows(NullPointerException.class, () -> Travel.builder().endPoint("A").driverId(1L).startPoint("b").pricePerPerson(22.4).build());
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
