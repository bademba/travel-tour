package com.kendirita.travel_tour.controller;

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
import org.springframework.web.bind.annotation.*;

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
    private QuotationRepository quotationRepository;


    @PostMapping("/quote")
    public ResponseEntity createQuote(@RequestBody Map<String, Object> payload) {

        Quotation quotation = new Quotation();

        quotation.setQuoteNumber((String) payload.get("quoteNumber"));
        quotation.setTitle((String) payload.get("title"));
        quotation.setCurrency((String) payload.get("currency"));
        quotation.setItinerary((String) payload.get("itinerary"));
        quotation.setResidencyStatus((String) payload.get("residencyStatus"));
        quotation.setNotes((String) payload.get("notes"));

        quotation.setComparisonMode(payload.get("comparisonMode") != null && Boolean.parseBoolean(payload.get("comparisonMode").toString()));

        quotation.setPaxAdults(payload.get("paxAdults") == null ? 0 : Integer.parseInt(payload.get("paxAdults").toString()));

        quotation.setPaxChildren(payload.get("paxChildren") == null ? 0 : Integer.parseInt(payload.get("paxChildren").toString()));

        quotation.setPaxYteen(payload.get("paxYteen") == null ? 0 : Integer.parseInt(payload.get("paxYteen").toString()));

        quotation.setTotalAmount(payload.get("totalAmount") == null ? BigDecimal.ZERO : new BigDecimal(payload.get("totalAmount").toString()));

        quotation.setMarkupPercent(payload.get("markupPercent") == null ? BigDecimal.ZERO : new BigDecimal(payload.get("markupPercent").toString()));

        quotation.setStatus(QuotationStatus.from(payload.get("status").toString()));

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


        String createdBy = (String) payload.get("createdBy");
        String clientId  = (String) payload.get("clientId");

        Quotation savedQuotation =
                quotationService.createQuotation(quotation, createdBy, clientId);

        return ResponseHandler.generateResponse(UUID.randomUUID(), "Quote created successfully", HttpStatus.CREATED, savedQuotation, TimestampUtil.now());
    }

    @GetMapping("/quote/{id}")
    public  ResponseEntity<Object> searchByQuoteId(@PathVariable String id){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote details found",HttpStatus.OK,quotationService.searchById(id),TimestampUtil.now());
    }

    @GetMapping("/quote")
    public ResponseEntity<Object> listAllQuotations(){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quotes found",HttpStatus.OK,quotationService.listAllQuotations(),TimestampUtil.now());
    }

    @PutMapping("/quote/{id}")
    public ResponseEntity<Object> updateQuote(@RequestBody Map<String,Object> quotation , @PathVariable String id){
        Quotation currentQuotation = quotationService.searchById(id);
        if (currentQuotation==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }


        currentQuotation.setTitle((String) quotation.get("title"));
        //currentQuotation.setStartDate((Date) quotation.get("startDate"));
//        currentQuotation.setEndDate((Date) quotation.get("endDate"));
//        currentQuotation.setReturnDate((Date) quotation.get("returnDate"));
        currentQuotation.setPaxAdults((Integer) quotation.get("paxAdults"));
        currentQuotation.setPaxChildren((Integer) quotation.get("paxChildren"));
        currentQuotation.setPaxYteen((Integer) quotation.get("paxYteen"));
//        currentQuotation.setTotalAmount((BigDecimal) quotation.get("totalAmount"));
        currentQuotation.setCurrency((String) quotation.get("currency"));
        //currentQuotation.setStatus((String) quotation.get("status"));
        currentQuotation.setComparisonMode(
                quotation.get("comparisonMode") != null && (Boolean) quotation.get("comparisonMode")
        );
        currentQuotation.setItinerary((String) quotation.get("itinerary"));
        currentQuotation.setResidencyStatus((String) quotation.get("residencyStatus"));
//        currentQuotation.setMarkupPercent((BigDecimal) quotation.get("markupPercent"));
        currentQuotation.setNotes((String) quotation.get("notes"));




        String clientId  = (String) quotation.get("clientId");
        String createdBy = (String) quotation.get("createdBy");
        Quotation updatedQuotation = quotationService.updateQuotation(currentQuotation, clientId, createdBy);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote updated successfully",HttpStatus.OK,updatedQuotation,TimestampUtil.now());
    }
}
