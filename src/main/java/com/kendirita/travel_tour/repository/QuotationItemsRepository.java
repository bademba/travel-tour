package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.QuotationItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationItemsRepository extends JpaRepository<QuotationItems,String> {

}
