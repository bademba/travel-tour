package com.kendirita.travel_tour.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kendirita.travel_tour.util.HybridIdGenerator;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "quotations")
public class Quotation {

    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String id;

    @Column(name = "quote_number" , unique = true, nullable = false)
    private String quoteNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date startDate;

    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date endDate;

    @Column(name = "return_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date returnDate;

    @Column(name = "pax_adults")
    private int paxAdults;

    @Column(name = "pax_children")
    private int paxChildren;

    @Column(name = "pax_yteen")
    private int paxYteen;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "currency")
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private QuotationStatus status;

    @Column(name = "comparison_mode")
    private boolean comparisonMode;

    @Column(name = "itinerary")
    private String itinerary;

    @Column(name = "residency_status")
    private String residencyStatus;

    @Column(name = "markup_percent")
    private BigDecimal markupPercent;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User user;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date updatedAt;

    public Quotation(){}

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

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public QuotationStatus getStatus() {
        return status;
    }

    public void setStatus(QuotationStatus status) {
        this.status = status;
    }

    public boolean isComparisonMode() {
        return comparisonMode;
    }

    public void setComparisonMode(boolean comparisonMode) {
        this.comparisonMode = comparisonMode;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public String getResidencyStatus() {
        return residencyStatus;
    }

    public void setResidencyStatus(String residencyStatus) {
        this.residencyStatus = residencyStatus;
    }

    public BigDecimal getMarkupPercent() {
        return markupPercent;
    }

    public void setMarkupPercent(BigDecimal markupPercent) {
        this.markupPercent = markupPercent;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
