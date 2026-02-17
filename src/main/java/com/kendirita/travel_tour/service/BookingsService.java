package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.dto.BookingsDTO;
import com.kendirita.travel_tour.entity.Bookings;
import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.repository.BookingsRepository;
import com.kendirita.travel_tour.repository.ClientRepository;
import com.kendirita.travel_tour.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private QuotationRepository quotationRepository;
    @Autowired
    private ClientRepository clientRepository;

    public Bookings createBooking(BookingsDTO bookingsDTO){
        Bookings bookings=new Bookings();

        Quotation quotation = quotationRepository.findById(bookingsDTO.getQuotationId()).orElseThrow(() ->
                new IllegalArgumentException("Quotation not found"));
        Client client =clientRepository.findById(bookingsDTO.getClientId()).orElseThrow(()->new IllegalStateException("Client not found"));


        bookings.setQuotation(quotation);
        bookings.setClient(client);
        bookings.setBookingNumber(bookingsDTO.getBookingNumber());
        bookings.setTotalPaid(bookingsDTO.getTotalPaid());
        bookings.setPaymentStatus(bookingsDTO.getPaymentStatus());
        bookings.setBookingStatus(bookingsDTO.getBookingStatus());
        bookings.setCreatedAt(bookingsDTO.getCreatedAt());
        bookings.setUpdatedAt(bookingsDTO.getUpdatedAt());
        return bookingsRepository.save(bookings);
    }

    public List<Bookings> listAllBookings(){
        return bookingsRepository.findAll();
    }

    public Bookings searchByBookingNumber(String bookingNumber){
        return bookingsRepository.searchByBookingNumber(bookingNumber);
    }

    public boolean deletByBookinNUmber(String bookingNumber){
        Optional<Bookings> bookings = Optional.ofNullable(bookingsRepository.searchByBookingNumber(bookingNumber));
        if (bookings.isEmpty()) {
            return false;
        }
        bookingsRepository.delete(bookings.get());
        return true;
    }
}
