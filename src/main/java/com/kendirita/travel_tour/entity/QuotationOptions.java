package com.kendirita.travel_tour.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "quotation_options")
public class QuotationOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quotation_id", nullable = false)
    private Quotation quotation;

    @Column(name="option_name")
    private String optionName;

    @Column(name = "option_description")
    private String optionDescription;

    @Column(name = "is_selected")
    private Boolean isSelected;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name="total_selling")
    private BigDecimal totalSelling;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date createdAt;


    public QuotationOptions(){}

    @PrePersist
    void onCreate() {
        this.createdAt = new Date();
    }


    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalSelling() {
        return totalSelling;
    }

    public void setTotalSelling(BigDecimal totalSelling) {
        this.totalSelling = totalSelling;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
