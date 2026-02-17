package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings,String> {
    Bookings searchById(String id);
    Bookings searchByBookingNumber(String bookingNumber);
}
