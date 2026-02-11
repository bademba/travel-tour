package com.kendirita.travel_tour.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kendirita.travel_tour.util.HybridIdGenerator;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "quotation_items")
public class QuotationItems {
    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quotation_id")
    @JsonIgnore
    private Quotation quotation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    @JsonIgnore
    private QuotationOptions quotationOptions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @JsonIgnore
    private Suppliers suppliers;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    @JsonIgnore
    private Destination destination;

    @Column(name="category")
    private String category;

    @Column(name="description")
    private String description;

    @Column(name = "day_number")
    private int dayNumber;

    @Column(name="service_date")
    private Date serviceDate;

    @Column(name = "nights")
    private int nights;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "pax_adults")
    private int paxAdults;

    @Column(name = "pax_children")
    private int paxChildren;

    @Column(name = "pax_yteen")
    private int  paxYteen;

    @Column(name = "unit_cost")
    private BigDecimal unitCost;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "selling_price")
    private BigDecimal sellingPrice;

    @Column(name = "details")
    private String details;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date updatedAt;

    public QuotationItems(){}

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        if (this.id == null) {
            this.id = HybridIdGenerator.generate();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public QuotationOptions getQuotationOptions() {
        return quotationOptions;
    }

    public void setQuotationOptions(QuotationOptions quotationOptions) {
        this.quotationOptions = quotationOptions;
    }

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
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
