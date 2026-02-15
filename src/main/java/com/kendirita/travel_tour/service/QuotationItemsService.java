package com.kendirita.travel_tour.service;


import com.kendirita.travel_tour.dto.QuotationItemsRequest;
import com.kendirita.travel_tour.entity.*;
import com.kendirita.travel_tour.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationItemsService {
    @Autowired
    private QuotationItemsRepository quotationItemsRepository;

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private QuotationOptionsRepository quotationOptionsRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private DestinationRepository destinationRepository;


    public QuotationItems createQuoteItems(QuotationItemsRequest quotationItemsRequest){
        QuotationItems quotationItems= new QuotationItems();
        Quotation quotation = quotationRepository.findById(quotationItemsRequest.getQuotationId()).orElseThrow(() ->
                new IllegalArgumentException("Quotation not found"));
        QuotationOptions quotationOptions =quotationOptionsRepository.findById(quotationItemsRequest.getOptionId()).orElseThrow(()->new IllegalStateException("Quote option not found"));

        Suppliers suppliers=supplierRepository.findById(quotationItemsRequest.getSupplierId()).orElseThrow(()->new IllegalStateException("Supplier not found"));

        Destination destination=destinationRepository.findById(quotationItemsRequest.getDestinationId()).orElseThrow(()->new IllegalStateException("Destination not found"));

        quotationItems.setQuotation(quotation);
        quotationItems.setQuotationOptions(quotationOptions);
        quotationItems.setSuppliers(suppliers);
        quotationItems.setDestination(destination);
        quotationItems.setCategory(quotationItemsRequest.getCategory());
        quotationItems.setDescription(quotationItemsRequest.getDescription());
        quotationItems.setDayNumber(quotationItemsRequest.getDayNumber());
        quotationItems.setServiceDate(quotationItemsRequest.getServiceDate());
        quotationItems.setNights(quotationItemsRequest.getNights());
        quotationItems.setQuantity(quotationItemsRequest.getQuantity());
        quotationItems.setPaxAdults(quotationItemsRequest.getPaxAdults());
        quotationItems.setPaxChildren(quotationItemsRequest.getPaxChildren());
        quotationItems.setPaxYteen(quotationItemsRequest.getPaxYteen());
        quotationItems.setUnitCost(quotationItemsRequest.getUnitCost());
        quotationItems.setTotalCost(quotationItemsRequest.getTotalCost());
        quotationItems.setSellingPrice(quotationItemsRequest.getSellingPrice());
        quotationItems.setDetails(quotationItemsRequest.getDetails());
        quotationItems.setNotes(quotationItemsRequest.getNotes());

        return quotationItemsRepository.save(quotationItems);
    }

    public List<QuotationItems> listQuoteItems(){
        return quotationItemsRepository.findAll();
    }
}
