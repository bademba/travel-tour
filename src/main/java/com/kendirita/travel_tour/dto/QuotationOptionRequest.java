package com.kendirita.travel_tour.dto;

import java.math.BigDecimal;

public class QuotationOptionRequest {
    private String quotationId;
    private String optionName;
    private String optionDescription;
    private Boolean isSelected;
    private BigDecimal totalCost;
    private BigDecimal totalSelling;

    public String getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(String quotationId) {
        this.quotationId = quotationId;
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

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
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
}
