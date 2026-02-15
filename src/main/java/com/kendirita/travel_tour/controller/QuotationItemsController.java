package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.QuotationItemsRequest;
import com.kendirita.travel_tour.entity.QuotationItems;
import com.kendirita.travel_tour.repository.QuotationItemsRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.QuotationItemsService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class QuotationItemsController {
    @Autowired
    private QuotationItemsRepository quotationItemsRepository;

    @Autowired
    private QuotationItemsService quotationItemsService;

    @PostMapping("/quote-item")
    public ResponseEntity<?> createQuoteItems(@RequestBody QuotationItemsRequest quotationItemsRequest){
        QuotationItems createdQuotationItems = quotationItemsService.createQuoteItems(quotationItemsRequest);

        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote Item created", HttpStatus.CREATED,createdQuotationItems, TimestampUtil.now());
    }

    @GetMapping("/quote-item")
    public ResponseEntity<Object> listAllQuoteItems(){
        List<QuotationItems> quoteItemsList = quotationItemsService.listQuoteItems();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote Items found",HttpStatus.OK,quoteItemsList,TimestampUtil.now());
    }

}
