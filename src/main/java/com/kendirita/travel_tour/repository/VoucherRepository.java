package com.kendirita.travel_tour.repository;

import com.kendirita.travel_tour.entity.Vouchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Vouchers , String> {
    Vouchers searchById(String id);
    Vouchers searchByVoucherNumber(String voucherNumber);
}
