package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.dto.QuotationOptionRequest;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.QuotationOptions;
import com.kendirita.travel_tour.exception.ResourceNotFoundException;
import com.kendirita.travel_tour.repository.QuotationOptionsRepository;
import com.kendirita.travel_tour.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationOptionsService {
    @Autowired
    private QuotationOptionsRepository quotationOptionsRepository;


    @Autowired
    private QuotationRepository quotationRepository;

    public QuotationOptions createQuotationOptions(QuotationOptionRequest request) {

        Quotation quotation = quotationRepository.findById(request.getQuotationId()).orElseThrow(() ->
                        new IllegalArgumentException("Quotation not found"));

        QuotationOptions option = new QuotationOptions();
        option.setQuotation(quotation);
        option.setOptionName(request.getOptionName());
        option.setOptionDescription(request.getOptionDescription());
        option.setIsSelected(request.getIsSelected());
        option.setTotalCost(request.getTotalCost());
        option.setTotalSelling(request.getTotalSelling());

        return quotationOptionsRepository.save(option);
    }

    public List<QuotationOptions> listAllQuotationOptions(){
        return quotationOptionsRepository.findAll();
    }

    public QuotationOptions searchById(String id){
        return quotationOptionsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quote Option not found with ID " +id));
    }
}
