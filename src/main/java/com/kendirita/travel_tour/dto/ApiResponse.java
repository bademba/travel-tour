package com.kendirita.travel_tour.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private T data;
    private String message;
    private int status;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    private ApiResponse(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    // ✅ Success factory method
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message, 201);
    }

    // Optional: error responses later
    public static <T> ApiResponse<T> error(String message, int status) {
        return new ApiResponse<>(null, message, status);
    }

    // getters only (no setters needed)
    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
