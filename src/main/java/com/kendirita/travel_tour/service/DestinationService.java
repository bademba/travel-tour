package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.Destination;
import com.kendirita.travel_tour.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}