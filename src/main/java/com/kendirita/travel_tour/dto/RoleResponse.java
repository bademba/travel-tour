package com.kendirita.travel_tour.dto;

import com.kendirita.travel_tour.entity.UserRole;

public class RoleResponse {
    private String id;
    private String role;

    public static RoleResponse from(UserRole userRole){
        if (userRole==null){
            return null;
        }
        RoleResponse dto =new RoleResponse();
        dto.role= userRole.getRoles().name();
        return dto;
    }

    public String getRole() {
        return role;
    }
}
