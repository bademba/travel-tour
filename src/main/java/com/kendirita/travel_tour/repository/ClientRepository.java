package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,String> {
    boolean existsByEmail(String email);
    Client searchById(String id);
}
