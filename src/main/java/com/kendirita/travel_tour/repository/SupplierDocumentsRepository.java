package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.SupplierDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDocumentsRepository extends JpaRepository<SupplierDocuments, String> {
    SupplierDocuments searchById(String id);
}
