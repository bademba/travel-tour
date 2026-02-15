package com.kendirita.travel_tour.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kendirita.travel_tour.entity.Destination;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.QuotationOptions;
import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.util.HybridIdGenerator;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


public class QuotationItemsRequest {

    private String id;

    private String quotationId;
    private String optionId;
    private String supplierId;
    private String destinationId;

     private String category;

     private String description;

     private int dayNumber;

     private Date serviceDate;

     private int nights;

     private int quantity;

     private int paxAdults;

     private int paxChildren;

     private int  paxYteen;

     private BigDecimal unitCost;

     private BigDecimal totalCost;

     private BigDecimal sellingPrice;

     private String details;

     private String notes;

    public Date createdAt;

    public Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(String quotationId) {
        this.quotationId = quotationId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPaxAdults() {
        return paxAdults;
    }

    public void setPaxAdults(int paxAdults) {
        this.paxAdults = paxAdults;
    }

    public int getPaxChildren() {
        return paxChildren;
    }

    public void setPaxChildren(int paxChildren) {
        this.paxChildren = paxChildren;
    }

    public int getPaxYteen() {
        return paxYteen;
    }

    public void setPaxYteen(int paxYteen) {
        this.paxYteen = paxYteen;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
