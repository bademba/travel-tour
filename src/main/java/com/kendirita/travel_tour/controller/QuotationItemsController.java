package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.QuotationItemsRequest;
import com.kendirita.travel_tour.dto.QuotationOptionResponse;
import com.kendirita.travel_tour.entity.*;
import com.kendirita.travel_tour.repository.*;
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

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private QuotationOptionsRepository quotationOptionsRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private DestinationRepository destinationRepository;

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

    @GetMapping("/quote-item/{id}")
    public ResponseEntity<Object> searchByQuoteItemId(@PathVariable String id) {
        QuotationItems quotationItems = quotationItemsService.searchById(id);
        return ResponseHandler.generateResponse(UUID.randomUUID(), "Quote Item found", HttpStatus.OK, quotationItems, TimestampUtil.now());
    }

    @PutMapping("/quote-item/{id}")
    public ResponseEntity<Object> updateQuoteItem(@PathVariable String id, @RequestBody QuotationItemsRequest quotationItemsRequest){
            QuotationItems currentQuotationItems= quotationItemsService.searchById(id);
            if (currentQuotationItems==null){
                return  ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item with ID {} not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
            }

        //update quotationId field only
        if (quotationItemsRequest.getQuotationId() != null) {
            Quotation quotation = quotationRepository.findById(quotationItemsRequest.getQuotationId())
                    .orElseThrow(() -> new IllegalArgumentException("Quotation not found |--|"));
            currentQuotationItems.setQuotation(quotation);
        }

        //update supplierId field only
        if (quotationItemsRequest.getSupplierId() !=null){
            Suppliers suppliers=supplierRepository.searchById(quotationItemsRequest.getSupplierId());
            currentQuotationItems.setSuppliers(suppliers);
        }

        //update destinationId field only
        if (quotationItemsRequest.getDestinationId() !=null){
            Destination destination=destinationRepository.searchById(quotationItemsRequest.getDestinationId());
             currentQuotationItems.setDestination(destination);
        }

        //update quotationOptionId field only
        if (quotationItemsRequest.getOptionId() !=null){
            QuotationOptions quotationOptions=quotationOptionsRepository.searchById(quotationItemsRequest.getOptionId());
            currentQuotationItems.setQuotationOptions(quotationOptions);
        }

        currentQuotationItems.setCategory(quotationItemsRequest.getCategory());
        currentQuotationItems.setDescription(quotationItemsRequest.getDescription());
        currentQuotationItems.setDayNumber(quotationItemsRequest.getDayNumber());
        currentQuotationItems.setServiceDate(quotationItemsRequest.getServiceDate());
        currentQuotationItems.setNights(quotationItemsRequest.getNights());
        currentQuotationItems.setQuantity(quotationItemsRequest.getQuantity());
        currentQuotationItems.setPaxAdults(quotationItemsRequest.getPaxAdults());
        currentQuotationItems.setPaxChildren(quotationItemsRequest.getPaxChildren());
        currentQuotationItems.setPaxYteen(quotationItemsRequest.getPaxYteen());
        currentQuotationItems.setUnitCost(quotationItemsRequest.getUnitCost());
        currentQuotationItems.setTotalCost(quotationItemsRequest.getTotalCost());
        currentQuotationItems.setSellingPrice(quotationItemsRequest.getSellingPrice());
        currentQuotationItems.setDetails(quotationItemsRequest.getDetails());
        currentQuotationItems.setNotes(quotationItemsRequest.getNotes());

        QuotationItems quotationItems=quotationItemsRepository.save(currentQuotationItems);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote Item updated successfully",HttpStatus.OK,quotationItems,TimestampUtil.now());

    }


    @DeleteMapping("/quote-item/{id}")
    public ResponseEntity<Object> deleteQuoteItem(@PathVariable String id){
        boolean quoteItemToBeDeleted =quotationItemsService.deletById(id);
        if (!quoteItemToBeDeleted){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item deleted successfully",HttpStatus.NO_CONTENT,"",TimestampUtil.now());

    }

}
