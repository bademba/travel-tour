package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.entity.Destination;
import com.kendirita.travel_tour.repository.DestinationRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.DestinationService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class DestinationController {

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    DestinationService destinationService;

    @PostMapping("/destinations")
    public ResponseEntity createDestination(@RequestBody Destination destination){
        Destination createdDestination = destinationService.createDestination(destination);
        if (createdDestination==null){
            return  ResponseHandler.generateResponse(UUID.randomUUID(),"Destination already exists", HttpStatus.CONFLICT,null, TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Destination created successfully",HttpStatus.CREATED,createdDestination,TimestampUtil.now());
    }

}
