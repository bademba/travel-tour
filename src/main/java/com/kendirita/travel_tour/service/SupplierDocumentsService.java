package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.dto.SupplierDocumentsDTO;
import com.kendirita.travel_tour.entity.SupplierDocuments;
import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.entity.User;
import com.kendirita.travel_tour.repository.SupplierDocumentsRepository;
import com.kendirita.travel_tour.repository.SupplierRepository;
import com.kendirita.travel_tour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierDocumentsService {

    @Autowired
    private SupplierDocumentsRepository supplierDocumentsRepository;


    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    public SupplierDocuments createSupplierDocuments(SupplierDocumentsDTO supplierDocumentsDTO){
        SupplierDocuments supplierDocuments = new SupplierDocuments();

        Suppliers suppliers=supplierRepository.findById(supplierDocumentsDTO.getSupplierId()).orElseThrow(()->new IllegalStateException("Supplier not found"));
        User user=userRepository.findById(supplierDocumentsDTO.getUploadedBy()).orElseThrow(()->new IllegalStateException("User not found"));

        supplierDocuments.setSupplierId(suppliers);
        supplierDocuments.setUploadedBy(user);
        supplierDocuments.setDocumentName(supplierDocumentsDTO.getDocumentName());
        supplierDocuments.setDocumentType(supplierDocumentsDTO.getDocumentType());
        supplierDocuments.setFileUrl(supplierDocumentsDTO.getFileUrl());
        supplierDocuments.setUploadedAt(supplierDocumentsDTO.getUploadedAt());

        return supplierDocumentsRepository.save(supplierDocuments);
    }

    public List<SupplierDocuments> listAllSupplierDocuments(){
        return supplierDocumentsRepository.findAll();
    }

    public SupplierDocuments supplierDocumentDetails(String id){
        return supplierDocumentsRepository.findById(id).orElseThrow(()->new IllegalStateException("Supplier Document not found"));
    }

    public boolean deletById(String id){
        Optional<SupplierDocuments> supplierDocuments = Optional.ofNullable(supplierDocumentsRepository.searchById(id));
        if (supplierDocuments.isEmpty()) {
            return false;
        }
        supplierDocumentsRepository.delete(supplierDocuments.get());
        return true;
    }
}
