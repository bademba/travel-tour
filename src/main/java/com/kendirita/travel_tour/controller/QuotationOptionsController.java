package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.QuotationOptionRequest;
import com.kendirita.travel_tour.dto.QuotationOptionResponse;
import com.kendirita.travel_tour.dto.QuotationResponse;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.QuotationOptions;
import com.kendirita.travel_tour.repository.QuotationOptionsRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.QuotationOptionsService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class QuotationOptionsController {

    @Autowired
    private QuotationOptionsRepository quotationOptionsRepository;

    @Autowired
    private QuotationOptionsService quotationOptionsService;

    @PostMapping("/quote-option")
    public ResponseEntity<?> createQuotationOption(
            @RequestBody QuotationOptionRequest request) {

        QuotationOptions created = quotationOptionsService.createQuotationOptions(request);

        return ResponseHandler.generateResponse(UUID.randomUUID(), "Quote option created successfully", HttpStatus.CREATED, QuotationOptionResponse.from(created), TimestampUtil.now()
        );
    }

    @GetMapping("/quote-option")
    public ResponseEntity<Object> listAllQuotations(){
        List<QuotationOptions> quotationOptions =quotationOptionsService.listAllQuotationOptions();
        List<QuotationOptionResponse> quotationOptionResponseList=quotationOptions.stream().map(QuotationOptionResponse::from).toList();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quotation Options found",HttpStatus.OK,quotationOptionResponseList,TimestampUtil.now());
    }

    @GetMapping("/quote-option/{id}")
    public ResponseEntity<Object> searchByQuoteOptionId(@PathVariable String id){
        QuotationOptions quotationOptions = quotationOptionsService.searchById(id);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote Option found",HttpStatus.OK,QuotationOptionResponse.from(quotationOptions),TimestampUtil.now());
    }
}
