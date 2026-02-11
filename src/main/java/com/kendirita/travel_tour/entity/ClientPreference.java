package com.kendirita.travel_tour.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kendirita.travel_tour.util.HybridIdGenerator;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "clients_preference")
public class ClientPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, unique = true,length = 10)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_id")
    @JsonIgnore
    private Client client;

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = HybridIdGenerator.generate();
        }
    }

    public ClientPreference() {}

    // getters & setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
