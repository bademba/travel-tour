package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.QuotationItemsRequest;
import com.kendirita.travel_tour.dto.QuotationTransportLegsRequest;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.QuotationItems;
import com.kendirita.travel_tour.entity.QuotationTransportLegs;
import com.kendirita.travel_tour.repository.QuotationItemsRepository;
import com.kendirita.travel_tour.repository.QuotationRepository;
import com.kendirita.travel_tour.repository.QuotationTransportLegsRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.QuotationTransportLegsService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class QuotationTransportLegsController {
    @Autowired
    private QuotationTransportLegsRepository quotationTransportLegsRepository;

    @Autowired
    private QuotationTransportLegsService quotationTransportLegsService;

    @Autowired
    private QuotationRepository quotationRepository;
    @Autowired
    private QuotationItemsRepository quotationItemsRepository;

    @PostMapping("/quote-transport-leg")
    public ResponseEntity<?> createQuoteTransportLeg(@RequestBody QuotationTransportLegsRequest quotationTransportLegsRequest){
        QuotationTransportLegs quotationTransportLegs = quotationTransportLegsService.createQuoteTransportLeg(quotationTransportLegsRequest);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote Transport leg created", HttpStatus.CREATED,quotationTransportLegs, TimestampUtil.now());
    }

    @GetMapping("/quote-transport-leg")
    public ResponseEntity<Object> listAllQuotationLegs(){
        List<QuotationTransportLegs> qtl = quotationTransportLegsService.listAllQuotationTransportLegs();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quotation Transport Legs found",HttpStatus.OK,qtl,TimestampUtil.now());
    }

    @GetMapping("/quote-transport-leg/{id}")
    public ResponseEntity<Object> searchQuoteTransportLegById(@PathVariable String id){
        QuotationTransportLegs quotationTransportLegs = quotationTransportLegsService.searchById(id);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quotation Transport Legs found",HttpStatus.OK,quotationTransportLegs,TimestampUtil.now());
    }

    @PutMapping("/quote-transport-leg/{id}")
    public ResponseEntity<Object> updateQuoteTransportLeg(@PathVariable String id, @RequestBody QuotationTransportLegsRequest quotationTransportLegsRequest){
        QuotationTransportLegs currentQuotationTransportLegs= quotationTransportLegsService.searchById(id);
        if (currentQuotationTransportLegs==null){
            return  ResponseHandler.generateResponse(UUID.randomUUID(),"Quote Transport Leg with ID {} not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        //update quotationId field only
        if (quotationTransportLegsRequest.getQuotationId() != null) {
            Quotation quotation = quotationRepository.findById(quotationTransportLegsRequest.getQuotationId())
                    .orElseThrow(() -> new IllegalArgumentException("Quotation not found |--|"));
            currentQuotationTransportLegs.setQuotation(quotation);
        }

        //updating quote item id only
        if (quotationTransportLegsRequest.getItemId() !=null){
            QuotationItems quotationItems =quotationItemsRepository.findById(quotationTransportLegsRequest.getItemId())
                    .orElseThrow(()->new IllegalStateException("Quote Item not found"));
            currentQuotationTransportLegs.setQuotationItems(quotationItems);
        }

        currentQuotationTransportLegs.setRouteFrom(quotationTransportLegsRequest.getRouteFrom());
        currentQuotationTransportLegs.setRouteTo(quotationTransportLegsRequest.getRouteTo());
        currentQuotationTransportLegs.setDistanceKm(quotationTransportLegsRequest.getDistanceKm());
        currentQuotationTransportLegs.setVehicleCount(quotationTransportLegsRequest.getVehicleCount());
        currentQuotationTransportLegs.setVehicleType(quotationTransportLegsRequest.getVehicleType());
        currentQuotationTransportLegs.setTotalCost(quotationTransportLegsRequest.getTotalCost());

        QuotationTransportLegs quotationTransportLegs=quotationTransportLegsRepository.save(currentQuotationTransportLegs);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quotation Transport Leg updated successfully",HttpStatus.OK,quotationTransportLegs,TimestampUtil.now());
    }

    @DeleteMapping("/quote-transport-leg/{id}")
    public ResponseEntity<Object> deleteQuoteTransportLeg(@PathVariable String id){
        boolean quoteLegToBeDeleted =quotationTransportLegsService.deleteById(id);
        if (!quoteLegToBeDeleted){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item deleted successfully",HttpStatus.NO_CONTENT,"",TimestampUtil.now());

    }
}
