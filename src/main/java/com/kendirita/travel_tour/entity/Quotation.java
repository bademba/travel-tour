package com.kendirita.travel_tour.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "quotations")
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "quote_number")
    private String quoteNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "return_date")
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

    //To be looked at later //
    private QuotationStatus status;

    @Column(name = "comparison_mode")
    private boolean comparisonMode;



}
