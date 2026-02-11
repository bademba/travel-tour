package com.kendirita.travel_tour.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kendirita.travel_tour.util.HybridIdGenerator;
import jakarta.persistence.*;

 import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "destinations", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Destination {

    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String id;

    @Column(name="name")
    public String name;

    @Column(name="country")
    public String country;

    @Column(name = "region")
    public String region;

    @Column(name = "description")
    public String description;

    @Column(name = "park_fees_adult")
    public BigDecimal parkFeesAdult;

    @Column(name = "park_fees_child")
    public BigDecimal parkFeesChild;

    @Column(name = "best_season")
    public String bestSeason;

    @Column(name = "climate")
    public String climate;

    @Column(name = "visa_info")
    public String visaInfo;

    @Column(name = "image_url")
    public String imageUrl;

    @Column(name = "is_active")
    public boolean isActive;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date updatedAt;

    public Destination(){}

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        if (this.id == null) {
            this.id = HybridIdGenerator.generate();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getParkFeesAdult() {
        return parkFeesAdult;
    }

    public void setParkFeesAdult(BigDecimal parkFeesAdult) {
        this.parkFeesAdult = parkFeesAdult;
    }

    public BigDecimal getParkFeesChild() {
        return parkFeesChild;
    }

    public void setParkFeesChild(BigDecimal parkFeesChild) {
        this.parkFeesChild = parkFeesChild;
    }

    public String getBestSeason() {
        return bestSeason;
    }

    public void setBestSeason(String bestSeason) {
        this.bestSeason = bestSeason;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getVisaInfo() {
        return visaInfo;
    }

    public void setVisaInfo(String visaInfo) {
        this.visaInfo = visaInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
