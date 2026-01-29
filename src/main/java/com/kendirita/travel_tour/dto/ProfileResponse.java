package com.kendirita.travel_tour.dto;

import com.kendirita.travel_tour.entity.Profile;


public class ProfileResponse {

    private String id;
    private String phone;
    private String avatarUrl;

    public static ProfileResponse from(Profile profile) {
        if (profile == null) return null;

        ProfileResponse dto = new ProfileResponse();
        dto.id = profile.getId();
        dto.phone = profile.getPhone();
        dto.avatarUrl = profile.getAvatarUrl();
        return dto;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}