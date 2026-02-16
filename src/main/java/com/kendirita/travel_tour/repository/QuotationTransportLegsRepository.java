package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.QuotationTransportLegs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationTransportLegsRepository extends JpaRepository<QuotationTransportLegs, String> {
    QuotationTransportLegsRepository searchById(String id);
}
