package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.User;
import com.kendirita.travel_tour.repository.ClientRepository;
import com.kendirita.travel_tour.repository.QuotationRepository;
import com.kendirita.travel_tour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {
    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Quotation createQuotation(Quotation quotation,String email,String clientId){
        if (quotationRepository.existsByQuoteNumber(quotation.getQuoteNumber())){
            throw new IllegalStateException("Quote number already exists");
        }

        User user = userRepository.findByEmail(email).orElseThrow(()-> new IllegalStateException("User not found"));
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("Client not found"));
        quotation.setUser(user);
        quotation.setClient(client);
        return quotationRepository.save(quotation);
    }
}
