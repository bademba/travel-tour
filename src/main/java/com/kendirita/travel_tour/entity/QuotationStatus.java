package com.kendirita.travel_tour.entity;

public enum QuotationStatus {
    draft,sent,confirmed,cancelled;

    public static QuotationStatus from(String value) {
        for (QuotationStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid quotation status: " + value);
    }
}
