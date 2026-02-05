package com.kendirita.travel_tour.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum QuotationStatus {
    draft,sent,confirmed,cancelled;

//    public static QuotationStatus from(String value) {
//        for (QuotationStatus status : values()) {
//            if (status.name().equalsIgnoreCase(value)) {
//                return status;
//            }
//        }
//        throw new IllegalArgumentException("Invalid quotation status: " + value);
//    }

    @JsonCreator
    public static QuotationStatus from(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        for (QuotationStatus status : values()) {
            if (status.name().equalsIgnoreCase(value.trim())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid quotation status: " + value);
    }
}
