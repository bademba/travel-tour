package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.repository.SupplierRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.SupplierService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @Autowired
    SupplierRepository supplierRepository;

    @PostMapping("/suppliers")
    public ResponseEntity createSupplier(@RequestBody Suppliers suppliers){
        Suppliers createdSuppliers = supplierService.createSupplier(suppliers);
        if (createdSuppliers==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier already exists", HttpStatus.MULTI_STATUS.CONFLICT,null, TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier created successfully",HttpStatus.CREATED,createdSuppliers,TimestampUtil.now());
    }
}
