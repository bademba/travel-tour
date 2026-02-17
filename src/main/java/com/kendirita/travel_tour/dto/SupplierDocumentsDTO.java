package com.kendirita.travel_tour.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.entity.User;
import jakarta.persistence.*;

import java.util.Date;


public class SupplierDocumentsDTO {

    private String id;


    private String supplierId;


    private String documentName;

     private String documentType;

     private String fileUrl;


    private String uploadedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date uploadedAt;


    public String getId() {
        return id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }
}
