package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,String> {
    boolean existsByName(String name);
}
