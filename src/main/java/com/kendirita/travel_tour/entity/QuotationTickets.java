package com.kendirita.travel_tour.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kendirita.travel_tour.util.HybridIdGenerator;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "quotation_tickets")
public class QuotationTickets {

    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quotation_id")
    @JsonIgnore
    private Quotation quotation;

    @Column(name = "ticket_type")
    private  String ticketType;

    @Column(name = "route")
    private String route;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "departure_time")
    private Date departureTime;

    @Column(name = "pax_adults")
    private int paxAdults;

    @Column(name = "pax_children")
    private int paxChildren;

    @Column(name = "pax_yteen")
    private int  paxYteen;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date createdAt;

    public QuotationTickets(){}

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        if (this.id == null) {
            this.id = HybridIdGenerator.generate();
        }
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

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
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
}
