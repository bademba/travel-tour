package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.dto.QuotationTicketsDTO;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.QuotationItems;
import com.kendirita.travel_tour.entity.QuotationTickets;
import com.kendirita.travel_tour.exception.ResourceNotFoundException;
import com.kendirita.travel_tour.repository.QuotationRepository;
import com.kendirita.travel_tour.repository.QuotationTicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationTicketsService {

    @Autowired
    private QuotationTicketsRepository quotationTicketsRepository;

    @Autowired
    private QuotationRepository quotationRepository;

    public QuotationTickets createQuoteTickets(QuotationTicketsDTO quotationTicketsDTO){
        QuotationTickets quotationTickets= new QuotationTickets();

        Quotation quotation = quotationRepository.findById(quotationTicketsDTO.getQuotationId()).orElseThrow(() ->
                new IllegalArgumentException("Quotation not found"));

        quotationTickets.setQuotation(quotation);
        quotationTickets.setTicketType(quotationTicketsDTO.getTicketType());
        quotationTickets.setRoute(quotationTicketsDTO.getRoute());
        quotationTickets.setDepartureDate(quotationTicketsDTO.getDepartureDate());
        quotationTickets.setDepartureTime(quotationTicketsDTO.getDepartureTime());
        quotationTickets.setPaxAdults(quotationTicketsDTO.getPaxAdults());
        quotationTickets.setPaxChildren(quotationTicketsDTO.getPaxChildren());
        quotationTickets.setPaxYteen(quotationTicketsDTO.getPaxYteen());
        quotationTickets.setTotalCost(quotationTicketsDTO.getTotalCost());
        quotationTickets.setNotes(quotationTicketsDTO.getNotes());
        quotationTickets.setCreatedAt(quotationTicketsDTO.getCreatedAt());
        return quotationTicketsRepository.save(quotationTickets);

    }

    public List<QuotationTickets> listAllQutationTickets(){
        return quotationTicketsRepository.findAll();
    }

    public QuotationTickets searchById(String id){
        return quotationTicketsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quote Ticket not found with ID " +id));
    }

    public boolean deletById(String id){
        Optional<QuotationTickets> quotationTickets = Optional.ofNullable(quotationTicketsRepository.searchById(id));
        if (quotationTickets.isEmpty()) {
            return false;
        }
        quotationTicketsRepository.delete(quotationTickets.get());
        return true;
    }
}
