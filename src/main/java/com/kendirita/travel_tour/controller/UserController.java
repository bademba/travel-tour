package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.entity.User;
import com.kendirita.travel_tour.repository.UserRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.UserService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user){
         User createdUser = userService.createUser(user);
         if(createdUser == null){
            return ResponseHandler.generateResponse(UUID.randomUUID(), "User already exists", HttpStatus.CONFLICT, null, TimestampUtil.now()
            );
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User created", HttpStatus.CREATED,createdUser,TimestampUtil.now());

    }

    @GetMapping("/users/{email}")
    public ResponseEntity<Object> searchBYEmail(@PathVariable String email){
        User userEmail = userService.searchByEmail(email);
        if(userEmail==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"User not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User details found",HttpStatus.OK,userEmail,TimestampUtil.now());
    }

    @GetMapping("/users")
    public ResponseEntity<Object> listUsers(){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Users found",HttpStatus.OK,userService.listUsers(),TimestampUtil.now());
    }

    @PutMapping("/users/{email}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable String email){
        User currentUser = userService.searchByEmail(email);
        if(currentUser==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"User not found",HttpStatus.NOT_FOUND,"",TimestampUtil.now());
        }
        currentUser.setFullName(user.getFullName());
        User updatedUser =userRepository.save(currentUser);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User updated",HttpStatus.OK,updatedUser,TimestampUtil.now());
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<Object> deleteUserByEmail(@PathVariable String email) {

        boolean deleted = userService.deleteByEmail(email);

        if (!deleted) {
            return ResponseHandler.generateResponse(UUID.randomUUID(), "User not found", HttpStatus.NOT_FOUND, null, TimestampUtil.now());
        }

        return ResponseHandler.generateResponse(UUID.randomUUID(), "User deleted successfully", HttpStatus.OK, null, TimestampUtil.now());
    }
}
