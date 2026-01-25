package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.entity.User;
import com.kendirita.travel_tour.repository.UserRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    RestTemplate restTemplate = new RestTemplate();

    Date date = new Date();
    SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String timestamp = DateFor.format(date);

    String methodName = "";

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody User user){
        methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        User createdUser = userService.createUser(user);
         if(createdUser == null){
            return ResponseHandler.generateResponse(UUID.randomUUID(), "User already exists", HttpStatus.CONFLICT, null, timestamp
            );
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User created", HttpStatus.CREATED,createdUser,timestamp);

    }
}
