package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.BookingsDTO;
import com.kendirita.travel_tour.entity.Bookings;
import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.repository.BookingsRepository;
import com.kendirita.travel_tour.repository.ClientRepository;
import com.kendirita.travel_tour.repository.QuotationRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.BookingsService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class BookingsController {
    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private BookingsService bookingsService;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody BookingsDTO bookingsDTO){
        Bookings createdBookings =  bookingsService.createBooking(bookingsDTO);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Bookking created successfully", HttpStatus.CREATED,createdBookings, TimestampUtil.now());
    }

    @GetMapping("/bookings")
    public ResponseEntity<Object> getAllBookings(){
        List<Bookings> bookingList =bookingsService.listAllBookings();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Booking list found",HttpStatus.OK,bookingList,TimestampUtil.now());
    }

    @GetMapping("/bookings/{bookingNumber}")
    public ResponseEntity<Object> bookingDetails(@PathVariable String bookingNumber){
        Bookings currentBooking=bookingsService.searchByBookingNumber(bookingNumber);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Booking details found",HttpStatus.OK,currentBooking,TimestampUtil.now());
    }

    @PutMapping("/bookings/{bookingNumber}")
    public ResponseEntity<Object> updateBooking(@PathVariable String bookingNumber, @RequestBody BookingsDTO bookingsDTO){
        Bookings currentBookings =  bookingsService.searchByBookingNumber(bookingNumber);
        if (currentBookings==null){
            return  ResponseHandler.generateResponse(UUID.randomUUID(),"Booking not found",HttpStatus.NOT_FOUND,null, TimestampUtil.now());
        }

        //update quotationId field only
        if (bookingsDTO.getQuotationId() != null) {
            Quotation quotation = quotationRepository.findById(bookingsDTO.getQuotationId())
                    .orElseThrow(() -> new IllegalArgumentException("Quotation not found |--|"));
            currentBookings.setQuotation(quotation);
        }

        if (bookingsDTO.getClientId() !=null){
            Client client =clientRepository.searchById(bookingsDTO.getClientId());
            currentBookings.setClient(client);
        }

        currentBookings.setTotalPaid(bookingsDTO.getTotalPaid());
        currentBookings.setPaymentStatus(bookingsDTO.getPaymentStatus());
        currentBookings.setBookingStatus(bookingsDTO.getBookingStatus());


        Bookings updatedBookings = bookingsRepository.save(currentBookings);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Booking updated succesfully",HttpStatus.OK,updatedBookings,TimestampUtil.now());
    }

    @DeleteMapping("/bookings/{bookingNumber}")
    public ResponseEntity<?> deleteBooking(@PathVariable String bookingNumber){
        boolean bookingToBeDeleted = bookingsService.deletByBookinNUmber(bookingNumber);
        if (!bookingToBeDeleted){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Booking not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Booking deleted",HttpStatus.NO_CONTENT,"",TimestampUtil.now());
    }

}
