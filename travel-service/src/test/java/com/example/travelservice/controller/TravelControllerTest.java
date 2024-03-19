package com.example.travelservice.controller;

import com.example.travelservice.bussines.TravelService;
import com.example.travelservice.model.Travel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelControllerTest {
//    @Autowired
//    private TravelController controller;
//    @MockBean
//    private TravelService service;
//
//    //happy flow
//    @Test
//    void getTravels_should_return_list_of_travels() throws Exception {
//        //arrange
//        List<Travel> destinationList = new ArrayList<>(List.of(Destination.builder().id(1L).build(), Destination.builder().id(2L).build()));
//        when(destinationService.findAll()).thenReturn(destinationList);
//
//        //act
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/destinations");
//        String content = (new ObjectMapper()).writeValueAsString(destinationList);
//        MockMvcBuilders.standaloneSetup(destinationController)
//                .build()
//                .perform(requestBuilder)
//                //assert
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string(content));
//    }

}
