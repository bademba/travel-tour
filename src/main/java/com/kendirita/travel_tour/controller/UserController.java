package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.entity.User;
import com.kendirita.travel_tour.repository.UserRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


    Date date = new Date();
    SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String timestamp = DateFor.format(date);


    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user){
         User createdUser = userService.createUser(user);
         if(createdUser == null){
            return ResponseHandler.generateResponse(UUID.randomUUID(), "User already exists", HttpStatus.CONFLICT, null, timestamp
            );
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User created", HttpStatus.CREATED,createdUser,timestamp);

    }

    @GetMapping("/users/{email}")
    public ResponseEntity<Object> searchBYEmail(@PathVariable String email){
        User userEmail = userService.searchByEmail(email);
        if(userEmail==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"User not found",HttpStatus.NOT_FOUND,null,timestamp);
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User details found",HttpStatus.OK,userEmail,timestamp);
    }

    @GetMapping("/users")
    public ResponseEntity<Object> listUsers(){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Users found",HttpStatus.OK,userService.listUsers(),timestamp);
    }

    @PutMapping("/users/{email}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable String email){
        User currentUser = userService.searchByEmail(email);
        if(currentUser==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"User not found",HttpStatus.NOT_FOUND,"",timestamp);
        }
        currentUser.setFullName(user.getFullName());
        User updatedUser =userRepository.save(currentUser);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User updated",HttpStatus.OK,updatedUser,timestamp);
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<Object> deleteUserByEmail(@PathVariable String email) {

        boolean deleted = userService.deleteByEmail(email);

        if (!deleted) {
            return ResponseHandler.generateResponse(UUID.randomUUID(), "User not found", HttpStatus.NOT_FOUND, null, timestamp);
        }

        return ResponseHandler.generateResponse(UUID.randomUUID(), "User deleted successfully", HttpStatus.OK, null, timestamp);
    }
}
