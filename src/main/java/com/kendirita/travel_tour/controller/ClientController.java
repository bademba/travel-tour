package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.repository.ClientRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.ClientService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;


    @PostMapping("/clients")
    public ResponseEntity createClient(@RequestBody Client client){
        Client createdClient = clientService.createClient(client);
        if(createdClient==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Client already exists", HttpStatus.CONFLICT,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Client created successfully",HttpStatus.CREATED,createdClient,TimestampUtil.now());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> searchClientById(@PathVariable String id ){
        Client clientId = clientService.searchClientById(id);
        if (clientId==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Client  not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Client Details found",HttpStatus.OK,clientService.searchClientById(id),TimestampUtil.now());
    }

    @GetMapping("/clients")
    public ResponseEntity<Object> listClients(){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Clients found",HttpStatus.OK,clientService.listClients(), TimestampUtil.now());
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable String id,@RequestBody Client client){
        Client currentClient = clientService.searchClientById(id);
        if (currentClient==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Client not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        currentClient.setFullName(client.getFullName());
        currentClient.setEmail(client.getEmail());
        currentClient.setPhone(client.getPhone());
        currentClient.setNationality(client.getNationality());
        currentClient.setPassportNumber(client.getPassportNumber());
        currentClient.setPassportExpiry(client.getPassportExpiry());
        currentClient.setAddress(client.getAddress());
        currentClient.setDietaryRequirements(client.getDietaryRequirements());
        currentClient.setAccessibilityNeeds(client.getAccessibilityNeeds());
        currentClient.setPreferences(client.getPreferences());
        currentClient.setUpdatedAt(client.getUpdatedAt());

        Client updatedClient=clientRepository.save(currentClient);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Client updated successfully",HttpStatus.OK,updatedClient,TimestampUtil.now());
    }


    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> deleteClientById(@PathVariable String id) {

        boolean deleted = clientService.deleteClientById(id);

        if (!deleted) {
            return ResponseHandler.generateResponse(UUID.randomUUID(), "Client not found", HttpStatus.NOT_FOUND, null, TimestampUtil.now());
        }

        return ResponseHandler.generateResponse(UUID.randomUUID(), "Client deleted successfully", HttpStatus.NO_CONTENT, null, TimestampUtil.now());
    }

}
