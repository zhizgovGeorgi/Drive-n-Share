package com.example.travelservice.controller;
import com.example.travelservice.bussines.TravelService;
import com.example.travelservice.converter.TravelConverter;
import com.example.travelservice.dto.CreateTravelRequest;
import com.example.travelservice.dto.FindTravelRequest;
import com.example.travelservice.dto.TravelResponse;
import com.example.travelservice.model.Travel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = TravelController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TravelControllerTest {
    @MockBean
    private TravelService service;
    @Autowired
    private TravelController controller;

    @Test
    void testCreateTravel_shouldSuccessfullyReturnCreatedTravel() throws Exception {
        //ARRANGE
        CreateTravelRequest request = CreateTravelRequest.builder().departureDate("27-12-2002").driverId(1L).pricePerPerson(27.12).startPoint("a").endPoint("b").build();
        TravelResponse response = TravelResponse.builder().departureDate("27-12-2002").driverId(1L).pricePerPerson(27.12).startPoint("a").endPoint("b").build();
        String content = new ObjectMapper().writeValueAsString(request);
        String expected = new ObjectMapper().writeValueAsString(response);
        //ACT
        when(service.save(any(Travel.class))).thenReturn(new Travel(1L, request.getStartPoint(), request.getEndPoint(), request.getPricePerPerson(), request.getDriverId(), request.getDepartureDate()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/travels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //ASSERT
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }



    @Test
    void testGetTravelByStartPoint_shouldSuccessfullyReturnTravels() throws Exception {
        //ARRANGE
        FindTravelRequest request = FindTravelRequest.builder().startPoint("a").endPoint("b").build();
        List<Travel> travels = new ArrayList<>();
        Travel travel = Travel.builder().id(1L).startPoint("a").endPoint("d").departureDate("04-04-2025").pricePerPerson(12).driverId(1L).build();
        travels.add(Travel.builder().id(1L).startPoint("a").endPoint("d").departureDate("04-04-2025").pricePerPerson(12).driverId(1L).build());
        travels.add(Travel.builder().id(2L).startPoint("b").endPoint("e").departureDate("04-04-2025").pricePerPerson(13).driverId(2L).build());
        travels.add(Travel.builder().id(3L).startPoint("c").endPoint("f").departureDate("04-04-2025").pricePerPerson(14).driverId(2L).build());
        List<TravelResponse> result = travels.stream().map(TravelConverter::travelToResponse).toList();
        String content = new ObjectMapper().writeValueAsString(request);
        String expected = (new ObjectMapper()).writeValueAsString(List.of(result.get(0)));

        //ACT
        when(service.findByStartPoint(any(String.class))).thenReturn(List.of(travel));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/travels/findSP")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(content);

        //ASSERT
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @Test
    void testGetTravelByEndPoint_shouldSuccessfullyReturnTravels() throws Exception {
        //ARRANGE
        FindTravelRequest request = FindTravelRequest.builder().startPoint("a").endPoint("d").build();
        List<Travel> travels = new ArrayList<>();
        Travel travel = Travel.builder().id(1L).startPoint("a").endPoint("d").departureDate("04-04-2025").pricePerPerson(12).driverId(1L).build();
        travels.add(Travel.builder().id(1L).startPoint("a").endPoint("d").departureDate("04-04-2025").pricePerPerson(12).driverId(1L).build());
        travels.add(Travel.builder().id(2L).startPoint("b").endPoint("e").departureDate("04-04-2025").pricePerPerson(13).driverId(2L).build());
        travels.add(Travel.builder().id(3L).startPoint("c").endPoint("f").departureDate("04-04-2025").pricePerPerson(14).driverId(2L).build());
        List<TravelResponse> result = travels.stream().map(TravelConverter::travelToResponse).toList();
        String content = new ObjectMapper().writeValueAsString(request);
        String expected = (new ObjectMapper()).writeValueAsString(List.of(result.get(0)));

        //ACT
        when(service.findByEndPoint(any(String.class))).thenReturn(List.of(travel));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/travels/findEP")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        //ASSERT
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @Test
    void testGetTravelByStartAndEndPoint_shouldSuccessfullyReturnTravels() throws Exception {
        //ARRANGE
        FindTravelRequest request = FindTravelRequest.builder().startPoint("a").endPoint("d").build();
        List<Travel> travels = new ArrayList<>();
        Travel travel = Travel.builder().id(1L).startPoint("a").endPoint("d").departureDate("04-04-2025").pricePerPerson(12).driverId(1L).build();
        travels.add(Travel.builder().id(1L).startPoint("a").endPoint("d").departureDate("04-04-2025").pricePerPerson(12).driverId(1L).build());
        travels.add(Travel.builder().id(2L).startPoint("b").endPoint("e").departureDate("04-04-2025").pricePerPerson(13).driverId(2L).build());
        travels.add(Travel.builder().id(3L).startPoint("c").endPoint("f").departureDate("04-04-2025").pricePerPerson(14).driverId(2L).build());
        List<TravelResponse> result = travels.stream().map(TravelConverter::travelToResponse).toList();
        String content = new ObjectMapper().writeValueAsString(request);
        String expected = (new ObjectMapper()).writeValueAsString(List.of(result.get(0)));

        //ACT
        when(service.findByStartAndEndPoint(any(String.class), any(String.class))).thenReturn(List.of(travel));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/travels/findSEP")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        //ASSERT
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }


    @Test
    void testGetTravel_shouldSuccessfullyReturnListOfTravels() throws Exception {
        //ARRANGE
        List<Travel> travels = new ArrayList<>();
        travels.add(Travel.builder().id(1L).startPoint("a").endPoint("d").departureDate("04-04-2025").pricePerPerson(12).driverId(1L).build());
        travels.add(Travel.builder().id(1L).startPoint("b").endPoint("e").departureDate("04-04-2025").pricePerPerson(13).driverId(2L).build());
        travels.add(Travel.builder().id(1L).startPoint("c").endPoint("f").departureDate("04-04-2025").pricePerPerson(14).driverId(2L).build());
        List<TravelResponse> result = travels.stream().map(TravelConverter::travelToResponse).toList();
        String expected = (new ObjectMapper()).writeValueAsString(result);

        //ACT
        when(service.findAll()).thenReturn(travels);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/travels")
                .contentType(MediaType.APPLICATION_JSON);

        //ASSERT
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }
}
