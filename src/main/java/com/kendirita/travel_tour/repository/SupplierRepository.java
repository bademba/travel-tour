package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository  extends JpaRepository<Suppliers,String> {
    boolean existsByEmail(String email);
    Suppliers searchByEmail(String email);
    Suppliers deleteByEmail(String email);
    Suppliers searchById(String id);
}
