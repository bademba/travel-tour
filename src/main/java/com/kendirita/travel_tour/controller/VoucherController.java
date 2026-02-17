package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.VoucherDTO;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.Suppliers;
import com.kendirita.travel_tour.entity.VoucherStatus;
import com.kendirita.travel_tour.entity.Vouchers;
import com.kendirita.travel_tour.repository.QuotationRepository;
import com.kendirita.travel_tour.repository.SupplierRepository;
import com.kendirita.travel_tour.repository.VoucherRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.VoucherService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class VoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @PostMapping("/vouchers")
    public ResponseEntity<?> createVoucher(@RequestBody VoucherDTO voucherDTO){
        Vouchers vouchers= voucherService.createVoucher(voucherDTO);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Voucher created successfully", HttpStatus.CREATED,vouchers, TimestampUtil.now());
    }

    @GetMapping("/vouchers")
    public ResponseEntity<Object> listAllVouchers(){
        List<Vouchers> vouchersList = voucherService.listVouchers();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Vouchers found",HttpStatus.OK,vouchersList,TimestampUtil.now());
    }

    @GetMapping("/vouchers/{voucherNumber}")
    public ResponseEntity<Object> voucherDetails(@PathVariable String voucherNumber){
        Vouchers vouchers = voucherService.searchByVoucherNumber(voucherNumber);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Voucher found",HttpStatus.OK,vouchers,TimestampUtil.now());
    }

    @PutMapping("/vouchers/{voucherNumber}")
    public ResponseEntity<Object> updateVoucher(@PathVariable String voucherNumber, @RequestBody VoucherDTO voucherDTO){
        Vouchers currentVoucher = voucherService.searchByVoucherNumber(voucherNumber);

        if (currentVoucher==null){
            return  ResponseHandler.generateResponse(UUID.randomUUID(),"Voucher with ID {} not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }

        //update quotationId field only
        if (voucherDTO.getQuotationId() != null) {
            Quotation quotation = quotationRepository.findById(voucherDTO.getQuotationId())
                    .orElseThrow(() -> new IllegalArgumentException("Quotation not found |--|"));
            currentVoucher.setQuotation(quotation);
        }

        //update supplierId field only
        if (voucherDTO.getSupplierId() !=null){
            Suppliers suppliers=supplierRepository.searchById(voucherDTO.getSupplierId());
            currentVoucher.setSuppliers(suppliers);
        }

        currentVoucher.setType(voucherDTO.getType());
        currentVoucher.setServiceDate(voucherDTO.getServiceDate());
        currentVoucher.setGuestNames(voucherDTO.getGuestNames());
        currentVoucher.setPaxAdults(voucherDTO.getPaxAdults());
        currentVoucher.setPaxChildren(voucherDTO.getPaxChildren());
        currentVoucher.setDetails(voucherDTO.getDetails());
        currentVoucher.setStatus(voucherDTO.getStatus());
        currentVoucher.setCreatedAt(voucherDTO.getCreatedAt());
        currentVoucher.setUpdatedAt(voucherDTO.getUpdatedAt());

        Vouchers updatedVoucher =  voucherRepository.save(currentVoucher);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Voucher updated successfully",HttpStatus.OK,updatedVoucher,TimestampUtil.now());
    }

    @DeleteMapping("/vouchers/{voucherNumber}")
    public ResponseEntity<Object> deleteVoucheer(@PathVariable String voucherNumber){
        boolean voucherToBeDeleted =voucherService.deletByVoucherNumber(voucherNumber);
        if (!voucherToBeDeleted){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Voucher item not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Voucher deleted successfully",HttpStatus.NO_CONTENT,"",TimestampUtil.now());

    }

}
