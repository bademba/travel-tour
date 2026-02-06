package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.QuotationOptionRequest;
import com.kendirita.travel_tour.entity.QuotationOptions;
import com.kendirita.travel_tour.repository.QuotationOptionsRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.QuotationOptionsService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseHandler.generateResponse(UUID.randomUUID(), "Quote option created successfully", HttpStatus.CREATED, created, TimestampUtil.now()
        );
    }
}
