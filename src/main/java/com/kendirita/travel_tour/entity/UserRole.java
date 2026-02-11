package com.kendirita.travel_tour.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kendirita.travel_tour.util.HybridIdGenerator;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public  UserRole(){}

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = HybridIdGenerator.generate();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }


    public void setRoles(Roles role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
