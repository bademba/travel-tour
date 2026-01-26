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

    @GetMapping("/clients/{email}")
    public ResponseEntity<Object> searchClientByEmail(@PathVariable String email ){
        Client clientEmail = clientService.searchClientByEmail(email);
        if (clientEmail==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Client with email {email} not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Client Details found",HttpStatus.OK,clientService.searchClientByEmail(email),TimestampUtil.now());
    }

    @GetMapping("/clients")
    public ResponseEntity<Object> listClients(){
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Clients found",HttpStatus.OK,clientService.listClients(), TimestampUtil.now());
    }
}
