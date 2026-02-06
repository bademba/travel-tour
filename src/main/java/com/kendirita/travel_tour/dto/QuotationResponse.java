package com.kendirita.travel_tour.dto;

import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.User;

import java.math.BigDecimal;
import java.util.Date;

public class QuotationResponse {
    private String id;
    private String quoteNumber;
   // private Client clientId;
    private String title;
    private Date startDate;
    private Date endDate;
    private Date returnDate;
    private int paxAdults;
    private int paxChildren;
    private int paxYteen;
    private BigDecimal totalAmount;
    private String currency;
    private String status;
    private boolean comparisonMode;
    private String itinerary;
    private String residencyStatus;
    private BigDecimal markupPercent;
    private String notes;
    private String createdBy;
    private Date createdAt;
    private Date updatedAt;

    public static QuotationResponse from (Quotation quotation){
        QuotationResponse dto =new QuotationResponse();
        dto.id=quotation.getId();
        dto.quoteNumber=quotation.getQuoteNumber();
        dto.createdAt=quotation.getCreatedAt();
        dto.updatedAt=quotation.getUpdatedAt();
        dto.title=quotation.getTitle();
        dto.startDate=quotation.getStartDate();
        dto.endDate=quotation.getEndDate();
        dto.returnDate=quotation.getReturnDate();
        dto.paxAdults=quotation.getPaxAdults();
        dto.paxChildren=quotation.getPaxChildren();
        dto.paxYteen=quotation.getPaxYteen();
        dto.totalAmount=quotation.getTotalAmount();
        dto.currency=quotation.getCurrency();
        dto.status= String.valueOf(quotation.getStatus());
        dto.comparisonMode=quotation.isComparisonMode();
        dto.itinerary=quotation.getItinerary();
        dto.residencyStatus=quotation.getResidencyStatus();
        dto.markupPercent=quotation.getMarkupPercent();
        dto.notes=quotation.getNotes();
        dto.createdBy = quotation.getUser() != null
                ? quotation.getUser().getEmail()
                : null;

        return dto;
    }

    public String getId() {
        return id;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getReturnDate() {
        return returnDate;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }

    public boolean isComparisonMode() {
        return comparisonMode;
    }

    public String getItinerary() {
        return itinerary;
    }

    public String getResidencyStatus() {
        return residencyStatus;
    }

    public BigDecimal getMarkupPercent() {
        return markupPercent;
    }

    public String getNotes() {
        return notes;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
