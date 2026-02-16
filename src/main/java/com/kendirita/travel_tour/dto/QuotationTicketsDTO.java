package com.kendirita.travel_tour.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.util.HybridIdGenerator;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


public class QuotationTicketsDTO {

    private String id;

    private String quotationId;

    private  String ticketType;

     private String route;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
     private Date departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
     private Date departureTime;

     private int paxAdults;

     private int paxChildren;

     private int  paxYteen;

     private BigDecimal totalCost;

     private String notes;

    public Date createdAt;



    public String getId() {
        return id;
    }


    public String getQuotationId() {
        return quotationId;
    }


    public String getTicketType() {
        return ticketType;
    }

    public String getRoute() {
        return route;
    }


    public Date getDepartureDate() {
        return departureDate;
    }


    public Date getDepartureTime() {
        return departureTime;
    }

    public int getPaxAdults() {
        return paxAdults;
    }


    public int getPaxChildren() {
        return paxChildren;
    }


    public int getPaxYteen() {
        return paxYteen;
    }


    public BigDecimal getTotalCost() {
        return totalCost;
    }


    public String getNotes() {
        return notes;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

 }
