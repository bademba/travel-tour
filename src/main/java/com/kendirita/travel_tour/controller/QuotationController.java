package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.QuotationStatus;
import com.kendirita.travel_tour.repository.QuotationRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.QuotationService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @Autowired
    private ObjectMapper objectMapper;


//    @PostMapping("/quote")
//    public ResponseEntity createQuote(@RequestBody Map<String,Object> payload){
//        Quotation quotation = objectMapper.convertValue(payload, Quotation.class);
//        quotation.setQuoteNumber((String) payload.get("quoteNumber"));
//
//        quotation.setQuoteNumber((String) payload.get("quoteNumber"));
//        quotation.setTitle((String) payload.get("title"));
//        quotation.setStartDate((Date) payload.get("startDate"));
//        quotation.setEndDate((Date) payload.get("endDate"));
//        quotation.setReturnDate((Date) payload.get("returnDate"));
//        quotation.setPaxAdults((int) payload.get("paxAdults"));
//        quotation.setPaxChildren((int) payload.get("paxChildren"));
//        quotation.setPaxYteen((int) payload.get("paxYteen"));
//        quotation.setTotalAmount((BigDecimal) payload.get("totalAmount"));
//        quotation.setCurrency((String) payload.get("currency"));
//        quotation.setStatus(QuotationStatus.valueOf((String) payload.get("status")));
//        quotation.setComparisonMode((Boolean) payload.get("comparisonMode"));
//        quotation.setItinerary((String) payload.get("itinerary"));
//        quotation.setResidencyStatus((String) payload.get("residencyStatus"));
//        quotation.setMarkupPercent((BigDecimal) payload.get("markupPercent"));
//        quotation.setNotes((String) payload.get("notes"));
//        String createdBy = (String) payload.get("createdBy");
//        String clientId = (String) payload.get("clientId");
//        Quotation savedQuotation = quotationService.createQuotation(quotation, createdBy, clientId);
//
//        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote created successfully",HttpStatus.CREATED,savedQuotation,TimestampUtil.now());
//    }



    @PostMapping("/quote")
    public ResponseEntity createQuote(@RequestBody Map<String, Object> payload) {

        Quotation quotation = new Quotation();

        // ---------- SIMPLE STRINGS ----------
        quotation.setQuoteNumber((String) payload.get("quoteNumber"));
        quotation.setTitle((String) payload.get("title"));
        quotation.setCurrency((String) payload.get("currency"));
        quotation.setItinerary((String) payload.get("itinerary"));
        quotation.setResidencyStatus((String) payload.get("residencyStatus"));
        quotation.setNotes((String) payload.get("notes"));

        // ---------- BOOLEANS ----------
        quotation.setComparisonMode(
                payload.get("comparisonMode") != null &&
                        Boolean.parseBoolean(payload.get("comparisonMode").toString())
        );

        // ---------- INTEGERS ----------
        quotation.setPaxAdults(
                payload.get("paxAdults") == null ? 0 :
                        Integer.parseInt(payload.get("paxAdults").toString())
        );

        quotation.setPaxChildren(
                payload.get("paxChildren") == null ? 0 :
                        Integer.parseInt(payload.get("paxChildren").toString())
        );

        quotation.setPaxYteen(
                payload.get("paxYteen") == null ? 0 :
                        Integer.parseInt(payload.get("paxYteen").toString())
        );

        // ---------- BIGDECIMALS ----------
        quotation.setTotalAmount(
                payload.get("totalAmount") == null ? BigDecimal.ZERO :
                        new BigDecimal(payload.get("totalAmount").toString())
        );

        quotation.setMarkupPercent(
                payload.get("markupPercent") == null ? BigDecimal.ZERO :
                        new BigDecimal(payload.get("markupPercent").toString())
        );

        // ---------- ENUM ----------
        quotation.setStatus(
                QuotationStatus.from(payload.get("status").toString())
        );

        // ---------- DATES ----------
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (payload.get("startDate") != null)
                quotation.setStartDate(df.parse(payload.get("startDate").toString()));

            if (payload.get("endDate") != null)
                quotation.setEndDate(df.parse(payload.get("endDate").toString()));

            if (payload.get("returnDate") != null)
                quotation.setReturnDate(df.parse(payload.get("returnDate").toString()));

        } catch (ParseException | java.text.ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd HH:mm:ss");
        }

        // ---------- RELATION FIELDS ----------
        String createdBy = (String) payload.get("createdBy");
        String clientId  = (String) payload.get("clientId");

        Quotation savedQuotation =
                quotationService.createQuotation(quotation, createdBy, clientId);

        return ResponseHandler.generateResponse(
                UUID.randomUUID(),
                "Quote created successfully",
                HttpStatus.CREATED,
                savedQuotation,
                TimestampUtil.now()
        );
    }

    @GetMapping("/quote/{id}")
    public  ResponseEntity<Object> searchByQuoteId(@PathVariable String id){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote details found",HttpStatus.OK,quotationService.searchById(id),TimestampUtil.now());
    }

    @GetMapping("/quote")
    public ResponseEntity<Object> listAllQuotations(){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quotes found",HttpStatus.OK,quotationService.listAllQuotations(),TimestampUtil.now());
    }
}
