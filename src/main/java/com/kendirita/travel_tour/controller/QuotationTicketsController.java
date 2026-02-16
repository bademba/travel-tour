package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.QuotationItemsRequest;
import com.kendirita.travel_tour.dto.QuotationTicketsDTO;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.QuotationItems;
import com.kendirita.travel_tour.entity.QuotationTickets;
import com.kendirita.travel_tour.repository.QuotationRepository;
import com.kendirita.travel_tour.repository.QuotationTicketsRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.QuotationTicketsService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class QuotationTicketsController {

    @Autowired
    private QuotationTicketsService quotationTicketsService;

    @Autowired
    private QuotationRepository quotationRepository;
    @Autowired
    private QuotationTicketsRepository quotationTicketsRepository;

    @PostMapping("/quote-tickets")
    public ResponseEntity<?> createQuoteTicket(@RequestBody QuotationTicketsDTO quotationTicketsDTO){
        QuotationTickets quotationTickets=quotationTicketsService.createQuoteTickets(quotationTicketsDTO);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quotation Ticket created successfully", HttpStatus.CREATED,quotationTickets, TimestampUtil.now());
    }

    @GetMapping("/quote-tickets")
    public ResponseEntity<Object> listAllQuoteTickets(){
        List<QuotationTickets> quotationTickets = quotationTicketsService.listAllQutationTickets();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote Items found",HttpStatus.OK,quotationTickets,TimestampUtil.now());
    }

    @GetMapping("/quote-tickets/{id}")
    public ResponseEntity<Object> searchByQuoteTicketId(@PathVariable String id) {
        QuotationTickets quotationTickets = quotationTicketsService.searchById(id);
        return ResponseHandler.generateResponse(UUID.randomUUID(), "Quote Item found", HttpStatus.OK, quotationTickets, TimestampUtil.now());
    }

    @PutMapping("/quote-tickets/{id}")
    public ResponseEntity<Object> updateQuoteTicket(@PathVariable String id, @RequestBody QuotationTicketsDTO quotationTicketsDTO){
        QuotationTickets currentQuotationTickets= quotationTicketsService.searchById(id);
        if (currentQuotationTickets==null){
            return  ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item with ID {} not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }

        //update quotationId field only
        if (quotationTicketsDTO.getQuotationId() != null) {
            Quotation quotation = quotationRepository.findById(quotationTicketsDTO.getQuotationId())
                    .orElseThrow(() -> new IllegalArgumentException("Quotation not found |--|"));
            currentQuotationTickets.setQuotation(quotation);
        }

        currentQuotationTickets.setTicketType(quotationTicketsDTO.getTicketType());
        currentQuotationTickets.setRoute(quotationTicketsDTO.getRoute());
        currentQuotationTickets.setDepartureTime(quotationTicketsDTO.getDepartureTime());
        currentQuotationTickets.setDepartureDate(quotationTicketsDTO.getDepartureDate());
        currentQuotationTickets.setPaxAdults(quotationTicketsDTO.getPaxAdults());
        currentQuotationTickets.setPaxChildren(quotationTicketsDTO.getPaxChildren());
        currentQuotationTickets.setPaxYteen(quotationTicketsDTO.getPaxYteen());
        currentQuotationTickets.setTotalCost(quotationTicketsDTO.getTotalCost());
        currentQuotationTickets.setNotes(quotationTicketsDTO.getNotes());

        QuotationTickets quotationTickets=quotationTicketsRepository.save(currentQuotationTickets);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote Item updated successfully",HttpStatus.OK,quotationTickets,TimestampUtil.now());

    }


    @DeleteMapping("/quote-tickets/{id}")
    public ResponseEntity<Object> deleteQuoteTicket(@PathVariable String id){
        boolean quoteTicketToBeDeleted =quotationTicketsService.deletById(id);
        if (!quoteTicketToBeDeleted){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item deleted successfully",HttpStatus.NO_CONTENT,"",TimestampUtil.now());

    }
}
