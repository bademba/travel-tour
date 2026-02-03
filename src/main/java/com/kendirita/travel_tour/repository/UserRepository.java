package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);
    User searchByEmail(String email);
    Optional<User> findByEmail(String email);
}
