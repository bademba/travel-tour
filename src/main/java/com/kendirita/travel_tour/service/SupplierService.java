package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Suppliers createSupplier(Suppliers suppliers){
        if(supplierRepository.existsByEmail(suppliers.getEmail())){
            return null;
        }
        return supplierRepository.save(suppliers);
    }
}
