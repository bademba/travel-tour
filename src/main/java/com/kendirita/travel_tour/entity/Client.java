package com.kendirita.travel_tour.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kendirita.travel_tour.util.HybridIdGenerator;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String id;

    @Column(name="full_name")
    public String fullName;

    @Column(name="email", unique = true)
    public String email;

    @Column(name="phone")
    public String phone;

    @Column(name="nationality")
    public String nationality;

    @Column(name="passport_number")
    public String passportNumber;

    @Column(name="passport_expiry")
    public String passportExpiry;

    @Column(name="address")
    public String address;

    @Column(name="dietary_requirements")
    public String dietaryRequirements;

    @Column(name="accessibility_needs")
    public String accessibilityNeeds;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ClientPreference preferences;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date updatedAt;


    public Client(){}

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportExpiry() {
        return passportExpiry;
    }

    public void setPassportExpiry(String passportExpiry) {
        this.passportExpiry = passportExpiry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDietaryRequirements() {
        return dietaryRequirements;
    }

    public void setDietaryRequirements(String dietaryRequirements) {
        this.dietaryRequirements = dietaryRequirements;
    }

    public String getAccessibilityNeeds() {
        return accessibilityNeeds;
    }

    public void setAccessibilityNeeds(String accessibilityNeeds) {
        this.accessibilityNeeds = accessibilityNeeds;
    }

//    public String getPreferences() {
//        return preferences;
//    }
//
//    public void setPreferences(String preferences) {
//        this.preferences = preferences;
//    }

    public ClientPreference getPreferences() {
        return preferences;
    }

    public void setPreferences(ClientPreference preferences) {
        this.preferences = preferences;
        if (preferences != null) {
            preferences.setClient(this);
        }
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
