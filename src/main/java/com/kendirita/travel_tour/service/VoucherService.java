package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.dto.VoucherDTO;
import com.kendirita.travel_tour.entity.*;
import com.kendirita.travel_tour.repository.QuotationRepository;
import com.kendirita.travel_tour.repository.SupplierRepository;
import com.kendirita.travel_tour.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    public Vouchers createVoucher(VoucherDTO voucherDTO){
        Vouchers vouchers= new Vouchers();

        Quotation quotation = quotationRepository.findById(voucherDTO.getQuotationId()).orElseThrow(() -> new IllegalArgumentException("Quotation not found"));

        Suppliers suppliers=supplierRepository.findById(voucherDTO.getSupplierId()).orElseThrow(()->new IllegalStateException("Supplier not found"));

        vouchers.setQuotation(quotation);
        vouchers.setSuppliers(suppliers);
        vouchers.setVoucherNumber(voucherDTO.getVoucherNumber());
        vouchers.setType(voucherDTO.getType());
        vouchers.setServiceDate(voucherDTO.getServiceDate());
        vouchers.setGuestNames(voucherDTO.getGuestNames());
        vouchers.setPaxAdults(voucherDTO.getPaxAdults());
        vouchers.setPaxChildren(voucherDTO.getPaxChildren());
        vouchers.setDetails(voucherDTO.getDetails());
        vouchers.setDetails(voucherDTO.getDetails());

        return voucherRepository.save(vouchers);

    }

    public List<Vouchers> listVouchers(){
        return voucherRepository.findAll();
    }

    public Vouchers searchByVoucherNumber(String voucherNumber){
        return voucherRepository.searchByVoucherNumber(voucherNumber);
    }

    public boolean deletByVoucherNumber(String voucherNumber){
        Optional<Vouchers> vouchers = Optional.ofNullable(voucherRepository.searchByVoucherNumber(voucherNumber));
        if (vouchers.isEmpty()) {
            return false;
        }
        voucherRepository.delete(vouchers.get());
        return true;
    }
}
