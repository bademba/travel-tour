package com.kendirita.travel_tour.dto;

import com.kendirita.travel_tour.entity.QuotationOptions;

import java.math.BigDecimal;
import java.util.Date;

public class QuotationOptionResponse {
    private String id;
    private String quotationId;
    private String optionName;
    private String optionDescription;
    private Boolean isSelected;
    private BigDecimal totalCost;
    private BigDecimal totalSelling;
    private Date createdAt;


    public static QuotationOptionResponse from (QuotationOptions quotationOptions){
        QuotationOptionResponse dto = new QuotationOptionResponse();
        dto.id=quotationOptions.getId();
        dto.optionName=quotationOptions.getOptionName();
        dto.optionDescription=quotationOptions.getOptionDescription();
        dto.isSelected=quotationOptions.getIsSelected();
        dto.totalCost=quotationOptions.getTotalCost();
        dto.totalSelling=quotationOptions.getTotalSelling();
        dto.quotationId=quotationOptions.getQuotation().getId();
        dto.createdAt=quotationOptions.getCreatedAt();

        return dto;
    }

    public String getId() {
        return id;
    }

    public String getQuotationId() {
        return quotationId;
    }

    public String getOptionName() {
        return optionName;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public BigDecimal getTotalSelling() {
        return totalSelling;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
