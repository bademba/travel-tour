package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.repository.QuotationItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotationItemsService {
    @Autowired
    private QuotationItemsRepository quotationItemsRepository;


}
