package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.Destination;
import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public Destination createDestination(Destination destination) {

        if (destinationRepository.existsByName(destination.getName())) {
            return null;
        }
        return destinationRepository.save(destination);
    }

    public List<Destination> listAllDestinations(){
        return destinationRepository.findAll();
    }

    public Destination searchDestinationById(String id){
        return destinationRepository.searchById(id);
    }

    public  boolean deleteDestinationById(String id){
        Optional<Destination> destination = Optional.ofNullable(destinationRepository.searchById(id));
        if (destination.isEmpty()) {
            return false;
        }
        destinationRepository.delete(destination.get());
        return true;

    }
}