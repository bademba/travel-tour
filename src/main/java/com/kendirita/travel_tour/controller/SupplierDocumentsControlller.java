package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.QuotationItemsRequest;
import com.kendirita.travel_tour.dto.SupplierDocumentsDTO;
import com.kendirita.travel_tour.entity.QuotationItems;
import com.kendirita.travel_tour.entity.SupplierDocuments;
import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.entity.User;
import com.kendirita.travel_tour.repository.SupplierDocumentsRepository;
import com.kendirita.travel_tour.repository.SupplierRepository;
import com.kendirita.travel_tour.repository.UserRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.SupplierDocumentsService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class SupplierDocumentsControlller {
    @Autowired
    private SupplierDocumentsRepository supplierDocumentsRepository;

    @Autowired
    private SupplierDocumentsService supplierDocumentsService;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/supplier-documents")
    public ResponseEntity<?> createSupplierDocuments(@RequestBody SupplierDocumentsDTO supplierDocumentsDTO){
        SupplierDocuments createdSupplierDocuments = supplierDocumentsService.createSupplierDocuments(supplierDocumentsDTO);

        return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier Document created", HttpStatus.CREATED,createdSupplierDocuments, TimestampUtil.now());
    }

    @GetMapping("/supplier-documents")
    public ResponseEntity<Object> listAllSupplierDocuments(){
        List<SupplierDocuments> supplierDocumentsList = supplierDocumentsService.listAllSupplierDocuments();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier Documents found",HttpStatus.OK,supplierDocumentsList,TimestampUtil.now());
    }

    @GetMapping("/supplier-documents/{id}")
    public ResponseEntity<Object> searchBySupplierDocumentId(@PathVariable String id) {
        SupplierDocuments supplierDocuments = supplierDocumentsService.supplierDocumentDetails(id);
        return ResponseHandler.generateResponse(UUID.randomUUID(), "SupplierDocument found", HttpStatus.OK, supplierDocuments, TimestampUtil.now());
    }


    @PutMapping("/supplier-documents/{id}")
    public ResponseEntity<Object> updateSupplierDocuments(@PathVariable String id, @RequestBody SupplierDocumentsDTO supplierDocumentsDTO) {
        SupplierDocuments currentSupplierDocuments = supplierDocumentsService.supplierDocumentDetails(id);
        if (currentSupplierDocuments == null) {
            return ResponseHandler.generateResponse(UUID.randomUUID(), "Supplier Documents with ID {} not found", HttpStatus.NOT_FOUND, null, TimestampUtil.now());
        }

        //update supplierId field only
        if (supplierDocumentsDTO.getSupplierId() !=null){
            Suppliers suppliers=supplierRepository.searchById(supplierDocumentsDTO.getSupplierId());
            currentSupplierDocuments.setSupplierId(suppliers);
        }

        //update user id field
        if (supplierDocumentsDTO.getUploadedBy() != null) {
            User user = userRepository.findById(supplierDocumentsDTO.getUploadedBy())
                    .orElseThrow(() -> new IllegalArgumentException("User not found |--|"));
            currentSupplierDocuments.setUploadedBy(user);
        }

        currentSupplierDocuments.setDocumentName(supplierDocumentsDTO.getDocumentName());
        currentSupplierDocuments.setDocumentType(supplierDocumentsDTO.getDocumentType());
        currentSupplierDocuments.setFileUrl(supplierDocumentsDTO.getFileUrl());
        currentSupplierDocuments.setUploadedAt(supplierDocumentsDTO.getUploadedAt());

        SupplierDocuments updatedSuppierDocuments = supplierDocumentsRepository.save(currentSupplierDocuments);

        return ResponseHandler.generateResponse(UUID.randomUUID(),"Supplier Documents uploaded successfully",HttpStatus.OK,updatedSuppierDocuments,TimestampUtil.now());
    }

    @DeleteMapping("/supplier-documents/{id}")
    public ResponseEntity<Object> deleteSupplierDocuments(@PathVariable String id){
        boolean supplierDocumentsToBeDeleted = supplierDocumentsService.deletById(id);
        if (!supplierDocumentsToBeDeleted){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item deleted successfully",HttpStatus.NO_CONTENT,"",TimestampUtil.now());

    }

}
