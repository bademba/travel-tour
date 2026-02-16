package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.QuotationTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationTicketsRepository extends JpaRepository<QuotationTickets , String> {
    QuotationTickets searchById(String id);
}
