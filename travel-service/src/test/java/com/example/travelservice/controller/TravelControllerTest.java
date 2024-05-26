package com.example.travelservice.controller;
import com.example.travelservice.bussines.TravelService;
import com.example.travelservice.dto.CreateTravelRequest;
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
}
