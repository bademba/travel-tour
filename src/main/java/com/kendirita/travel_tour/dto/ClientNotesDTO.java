package com.kendirita.travel_tour.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.entity.NoteType;
import com.kendirita.travel_tour.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.util.Date;

public class ClientNotesDTO {

    private String id;


    private String clientId;


    private NoteType noteType;

    @Column(name = "content")
    private String content;


    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date createdAt;

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
