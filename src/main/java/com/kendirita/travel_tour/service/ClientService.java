package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    //Create a new client
    public Client createClient(Client client){
        if(clientRepository.existsByEmail(client.getEmail())) {
            return null;
        }
        return clientRepository.save(client);
    }
}
