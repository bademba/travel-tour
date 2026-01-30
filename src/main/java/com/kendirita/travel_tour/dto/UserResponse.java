package com.kendirita.travel_tour.dto;

import com.kendirita.travel_tour.entity.User;

import java.util.Date;

public class UserResponse {

    private String id;
    private String email;
    private String fullName;
    private Date createdAt;
    private Date updatedAt;
    private ProfileResponse profile;
    private RoleResponse role;

    public static UserResponse from(User user) {

        UserResponse dto = new UserResponse();
        dto.id = user.getId();
        dto.email = user.getEmail();
        dto.fullName = user.getFullName();
        dto.createdAt = user.getCreatedAt();
        dto.updatedAt = user.getUpdatedAt();
        dto.profile = ProfileResponse.from(user.getProfile());
        dto.role = RoleResponse.from(user.getUserRole());

        return dto;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public ProfileResponse getProfile() {
        return profile;
    }

    public RoleResponse getRole() {
        return role;
    }

}