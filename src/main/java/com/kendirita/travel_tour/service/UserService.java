package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.Profile;
import com.kendirita.travel_tour.entity.User;
import com.kendirita.travel_tour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //create new user
    @Transactional
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("User with this email already exists");
        }

        Profile profile = user.getProfile();

        if (profile != null) {
            profile.setUser(user);
            profile.setFullName(user.getFullName());
            profile.setEmail(user.getEmail());
        }

        return userRepository.save(user);
    }

    //search user by email
    public User searchByEmail(String email){
        return userRepository.searchByEmail(email);
    }

    //fetch all users
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public boolean deleteByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.searchByEmail(email));
        if (user.isEmpty()) {
            return false;
        }
        userRepository.delete(user.get());
        return true;
    }
}
