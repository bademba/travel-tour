package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.dto.QuotationTransportLegsRequest;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.QuotationItems;
import com.kendirita.travel_tour.entity.QuotationTransportLegs;
import com.kendirita.travel_tour.exception.ResourceNotFoundException;
import com.kendirita.travel_tour.repository.QuotationItemsRepository;
import com.kendirita.travel_tour.repository.QuotationRepository;
import com.kendirita.travel_tour.repository.QuotationTransportLegsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationTransportLegsService {

    @Autowired
    private QuotationTransportLegsRepository quotationTransportLegsRepository;

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private QuotationItemsRepository quotationItemsRepository;

    public QuotationTransportLegs createQuoteTransportLeg(QuotationTransportLegsRequest quotationTransportLegsRequest){
        QuotationTransportLegs quotationTransportLegs= new QuotationTransportLegs();


        Quotation quotation = quotationRepository.findById(quotationTransportLegsRequest.getQuotationId()).orElseThrow(() ->
                new IllegalArgumentException("Quotation not found"));

        QuotationItems quotationItems=quotationItemsRepository.findById(quotationTransportLegsRequest.getItemId()).orElseThrow(()-> new IllegalStateException("Quote Item not found"));

        quotationTransportLegs.setQuotation(quotation);
        quotationTransportLegs.setQuotationItems(quotationItems);
        quotationTransportLegs.setRouteFrom(quotationTransportLegsRequest.getRouteFrom());
        quotationTransportLegs.setRouteTo(quotationTransportLegsRequest.getRouteTo());
        quotationTransportLegs.setDistanceKm(quotationTransportLegsRequest.getDistanceKm());
        quotationTransportLegs.setVehicleType(quotationTransportLegsRequest.getVehicleType());
        quotationTransportLegs.setVehicleCount(quotationTransportLegsRequest.getVehicleCount());
        quotationTransportLegs.setCreatedAt(quotationTransportLegsRequest.getCreatedAt());
        quotationTransportLegs.setTotalCost(quotationTransportLegsRequest.getTotalCost());
        System.out.println("Incoming totalCost: " + quotationTransportLegsRequest.getTotalCost());

        QuotationTransportLegs saved =
                quotationTransportLegsRepository.save(quotationTransportLegs);

        System.out.println("After save: " + saved.getTotalCost());

        return saved;


       // return quotationTransportLegsRepository.save(quotationTransportLegs);
    }

    public List<QuotationTransportLegs> listAllQuotationTransportLegs(){
        return quotationTransportLegsRepository.findAll();
    }


    public QuotationTransportLegs searchById(String id){
        return quotationTransportLegsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quote Transport Leg not found with ID " +id));
    }

    public  boolean deleteById(String id){
        Optional<QuotationTransportLegs> quotationTransportLegs = quotationTransportLegsRepository.findById(id);
        if (quotationTransportLegs.isEmpty()) {
            return false;
        }
        quotationTransportLegsRepository.delete(quotationTransportLegs.get());
        return true;
    }
}
