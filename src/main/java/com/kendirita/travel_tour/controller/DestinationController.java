package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.entity.Destination;
import com.kendirita.travel_tour.repository.DestinationRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.DestinationService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/destinations")
    public ResponseEntity<Object> listAllDestinations(){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Destinations found",HttpStatus.OK,destinationService.listAllDestinations(),TimestampUtil.now());
    }


    @GetMapping("/destinations/{id}")
    public ResponseEntity<Object> searchDestinationById(@PathVariable String id){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Destination details found",HttpStatus.OK,destinationService.searchDestinationById(id),TimestampUtil.now());
    }

    @PutMapping("/destinations/{id}")
    public ResponseEntity<Object> updateDestination(@PathVariable String id, @RequestBody Destination destination){
        Destination currentDestination = destinationService.searchDestinationById(id);
        if (currentDestination==null){
            return  ResponseHandler.generateResponse(UUID.randomUUID(),"Destination not found",HttpStatus.OK,null,TimestampUtil.now());
        }
        currentDestination.setName(destination.getName());
        currentDestination.setCountry(destination.getCountry());
        currentDestination.setRegion(destination.getRegion());
        currentDestination.setDescription(destination.getDescription());
        currentDestination.setParkFeesAdult(destination.getParkFeesAdult());
        currentDestination.setParkFeesChild(destination.getParkFeesChild());
        currentDestination.setBestSeason(destination.getBestSeason());
        currentDestination.setClimate(destination.getClimate());
        currentDestination.setVisaInfo(destination.getVisaInfo());
        currentDestination.setImageUrl(destination.getImageUrl());
        currentDestination.setActive(destination.isActive());
        currentDestination.setUpdatedAt(destination.getUpdatedAt());

        Destination updatedDestination = destinationRepository.save(currentDestination);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Destination updated successfully",HttpStatus.OK,updatedDestination,TimestampUtil.now());
    }

    @DeleteMapping("/destinations/{id}")
    public ResponseEntity<Object> deleteDestination(@PathVariable String id){
        boolean destinationToBeDeleted = destinationService.deleteDestinationById(id);
        if (!destinationToBeDeleted){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Destination not found",HttpStatus.OK,null, TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Destination deleted successfully",HttpStatus.OK,"",TimestampUtil.now());
    }

}
