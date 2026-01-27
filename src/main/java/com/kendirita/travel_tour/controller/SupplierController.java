package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.repository.SupplierRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.SupplierService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Object> searchSupplierById(@PathVariable String id){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier details found",HttpStatus.OK,supplierService.searchById(id),TimestampUtil.now());
    }


    @GetMapping("/suppliers")
    public ResponseEntity<Object> listAllSuppliers(){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Suppliers found",HttpStatus.OK,supplierService.listAllSuppliers(),TimestampUtil.now());
    }


    @PutMapping("/suppliers/{id}")
    public ResponseEntity<Object> updateSupplierById(@PathVariable String id , @RequestBody Suppliers suppliers){
        Suppliers currentSupplier = supplierService.searchById(id);
        if (currentSupplier==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        currentSupplier.setName(suppliers.getName());
        currentSupplier.setType(suppliers.getType());
        currentSupplier.setEmail(suppliers.getEmail());
        currentSupplier.setPhone(suppliers.getPhone());
        currentSupplier.setAddress(suppliers.getAddress());
        currentSupplier.setContactPerson(suppliers.getContactPerson());
        currentSupplier.setCommissionRate(suppliers.getCommissionRate());
        currentSupplier.setRating(suppliers.getRating());
        currentSupplier.setNotes(suppliers.getNotes());
        currentSupplier.setActive(suppliers.isActive());
        currentSupplier.setUpdatedAt(suppliers.getUpdatedAt());

        Suppliers updatedSupplier = supplierRepository.save(currentSupplier);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier updated successfully",HttpStatus.OK,updatedSupplier,TimestampUtil.now());
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Object> deleteSupplierById(@PathVariable String id){
        boolean supplierToBeDeleted = supplierService.deleteById(id);
        if (!supplierToBeDeleted){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier deleted successfully",HttpStatus.OK,"",TimestampUtil.now());
    }

}
