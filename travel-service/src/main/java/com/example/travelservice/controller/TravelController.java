package com.example.travelservice.controller;

import com.example.travelservice.bussines.TravelService;
import com.example.travelservice.converter.TravelConverter;
import com.example.travelservice.dto.CreateTravelRequest;
import com.example.travelservice.dto.FindTravelRequest;
import com.example.travelservice.dto.TravelResponse;
import com.example.travelservice.model.Travel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/travels")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class TravelController {
    private final TravelService service;

    @GetMapping()
    public ResponseEntity<List<TravelResponse>> getTravels(){
//        return ResponseEntity.ok().body(service.findAll());
    List<Travel> travels = service.findAll();
    List<TravelResponse> responses = travels.stream().map(this::mapToResponse).toList();
    return ResponseEntity.ok().body(responses);

    }

    @PostMapping("/findSEP")
    public ResponseEntity<List<TravelResponse>> getTravelsByStartAndEndPoint(@RequestBody FindTravelRequest request){
        List<Travel> travels = service.findByStartAndEndPoint(request.getStartPoint(), request.getEndPoint());
        List<TravelResponse> responses = travels.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok().body(responses);

    }
    @PostMapping("/findSP")
    public ResponseEntity<List<TravelResponse>> getTravelsByStartPoint(@RequestBody FindTravelRequest request){
        List<Travel> travels = service.findByStartPoint(request.getStartPoint());
        List<TravelResponse> responses = travels.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok().body(responses);

    }
    @PostMapping("/findEP")
    public ResponseEntity<List<TravelResponse>> getTravelsByEmdPoint(@RequestBody FindTravelRequest request){
        List<Travel> travels = service.findByEndPoint(request.getEndPoint());
        List<TravelResponse> responses = travels.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok().body(responses);

    }

    @PostMapping("/driversTravel")
    public ResponseEntity<List<TravelResponse>> getDriversTravel(@PathVariable("driverId") Long driverId){
        List<Travel> travels = service.findByDriver(driverId);
        List<TravelResponse> responses = travels.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok().body(responses);

    }

    @PostMapping()
    public TravelResponse save(@RequestBody  CreateTravelRequest request){
        Travel travel = TravelConverter.requestToTravel(request);
        TravelResponse response = TravelConverter.travelToResponse(service.save(travel));
        return response;
    }

    @DeleteMapping()
    public String deleteTravel(@PathVariable("id") Long id){
        try {
            service.deleteById(id);
            return "Travel has been deleted";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    private TravelResponse mapToResponse(Travel travel){
        TravelResponse response = TravelConverter.travelToResponse(travel);
        return response;
    }
}
