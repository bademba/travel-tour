package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.exception.ResourceNotFoundException;
import com.kendirita.travel_tour.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public  Client searchClientById(String id){
        return clientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Client not found with ID "+id));
    }

    //list all clients
    public List<Client> listClients(){
        return clientRepository.findAll();
    }

    public boolean deleteClientById(String id){
        Optional<Client> client = Optional.ofNullable(clientRepository.searchById(id));
        if (client.isEmpty()) {
            return false;
        }
        clientRepository.delete(client.get());
        return true;

    }
}
