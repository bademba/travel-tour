package com.kendirita.travel_tour.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kendirita.travel_tour.entity.VoucherStatus;
import java.util.Date;
import java.util.List;

public class VoucherDTO {

    private String id;

     private String voucherNumber;


    private String quotationId;


    private String supplierId;

     private String type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
     private Date serviceDate;


    private List<String> guestNames;

     private int paxAdults;

     private int paxChildren;

     private String details;


    private VoucherStatus status;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date createdAt;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date updatedAt;

    public String getId() {
        return id;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public String getQuotationId() {
        return quotationId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getType() {
        return type;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public List<String> getGuestNames() {
        return guestNames;
    }

    public int getPaxAdults() {
        return paxAdults;
    }

    public int getPaxChildren() {
        return paxChildren;
    }

    public String getDetails() {
        return details;
    }

    public VoucherStatus getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
