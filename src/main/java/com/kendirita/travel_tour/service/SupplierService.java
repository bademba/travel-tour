package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Suppliers searchByEmail(String email){
        return supplierRepository.searchByEmail(email);
    }

    public Suppliers searchById(String id){
        return supplierRepository.searchById(id);
    }

    public List<Suppliers> listAllSuppliers(){
        return supplierRepository.findAll();
    }

    public boolean deleteById(String id){
        Optional<Suppliers> suppliers = Optional.ofNullable(supplierRepository.searchById(id));
        if (suppliers.isEmpty()) {
            return false;
        }
        supplierRepository.delete(suppliers.get());
        return true;
    }
}
