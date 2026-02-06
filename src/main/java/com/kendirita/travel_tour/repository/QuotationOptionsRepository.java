package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.QuotationOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationOptionsRepository extends JpaRepository<QuotationOptions,String> {
    QuotationOptions searchById(String id);
}
