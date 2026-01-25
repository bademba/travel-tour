package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.User;
import com.kendirita.travel_tour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        if(userRepository.existsByEmail(user.getEmail())) {
            return null; // indicate user exists
        }
        return userRepository.save(user);
    }
}
