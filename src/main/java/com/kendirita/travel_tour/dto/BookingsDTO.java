package com.kendirita.travel_tour.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kendirita.travel_tour.entity.BookingStatus;
import com.kendirita.travel_tour.entity.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

public class BookingsDTO {
    private String id;
    private String bookingNumber;
    private String quotationId;
    private String clientId;
    private BigDecimal totalPaid;
    private PaymentStatus paymentStatus;
    private BookingStatus bookingStatus;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date createdAt;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date updatedAt;

    public String getId() {
        return id;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }


    public String getQuotationId() {
        return quotationId;
    }

    public String getClientId() {
        return clientId;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
