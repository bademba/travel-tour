package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    //search client by email
    public  Client searchClientByEmail(String email){
        return clientRepository.searchByEmail(email);
    }

    //list all clients
    public List<Client> listClients(){
        return clientRepository.findAll();
    }
}
