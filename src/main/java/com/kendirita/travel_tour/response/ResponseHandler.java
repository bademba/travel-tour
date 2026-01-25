package com.kendirita.travel_tour.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(UUID responseId, String message, HttpStatus status, Object responseObj, String timestamp) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("responseId",responseId);
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("timestamp",timestamp);

        return new ResponseEntity<Object>(map, status);
    }

}
