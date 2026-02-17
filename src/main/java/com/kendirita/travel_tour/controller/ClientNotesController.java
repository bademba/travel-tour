package com.kendirita.travel_tour.controller;

import com.kendirita.travel_tour.dto.ClientNotesDTO;
import com.kendirita.travel_tour.entity.Client;
import com.kendirita.travel_tour.entity.ClientNotes;
import com.kendirita.travel_tour.entity.Quotation;
import com.kendirita.travel_tour.entity.User;
import com.kendirita.travel_tour.repository.ClientNotesRepository;
import com.kendirita.travel_tour.repository.ClientRepository;
import com.kendirita.travel_tour.repository.UserRepository;
import com.kendirita.travel_tour.response.ResponseHandler;
import com.kendirita.travel_tour.service.ClientNotesService;
import com.kendirita.travel_tour.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tour")
public class ClientNotesController {
    @Autowired
    private ClientNotesService clientNotesService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientNotesRepository clientNotesRepository;

    @PostMapping("/client-notes")
    public ResponseEntity<?> createCientNotes(@RequestBody ClientNotesDTO clientNotesDTO){
        ClientNotes  createdClientNotes=clientNotesService.createClientNotes(clientNotesDTO);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Client Notes created successfully", HttpStatus.CREATED,createdClientNotes, TimestampUtil.now());

    }

    @GetMapping("/client-notes")
    public ResponseEntity<Object> listAllClientNotes(){
       List<ClientNotes> clientNotes = clientNotesService.listAllClientNotes();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Client notes found",HttpStatus.OK,clientNotes,TimestampUtil.now());
    }

    @GetMapping("/client-notes/{id}")
    public ResponseEntity<Object>  clientNotesDetails(@PathVariable String id){
        ClientNotes clientNotes =clientNotesService.searchById(id);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Client notes found",HttpStatus.OK,clientNotes,TimestampUtil.now());
    }

    @PutMapping("/client-notes/{id}")
    public ResponseEntity<Object> updateClientNotes(@PathVariable String id,@RequestBody ClientNotesDTO clientNotesDTO){
        ClientNotes currentClientNotes = clientNotesService.searchById(id);
        if (currentClientNotes==null){
            return  ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item with ID {} not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }

        //updated client id
        if (clientNotesDTO.getClientId() != null) {
            Client client = clientRepository.findById(clientNotesDTO.getClientId())
                    .orElseThrow(() -> new IllegalArgumentException("Client not found |--|"));
            currentClientNotes.setClient(client);
        }

        //update user id field
        if (clientNotesDTO.getCreatedBy() != null) {
            User user = userRepository.findById(clientNotesDTO.getCreatedBy())
                    .orElseThrow(() -> new IllegalArgumentException("User not found |--|"));
            currentClientNotes.setCreatedBy(user);
        }

        currentClientNotes.setNoteType(clientNotesDTO.getNoteType());
        currentClientNotes.setContent(clientNotesDTO.getContent());

        ClientNotes updatedClientNotes =  clientNotesRepository.save(currentClientNotes);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Client notes updated successfully",HttpStatus.OK,updatedClientNotes,TimestampUtil.now());
    }

    @DeleteMapping("/client-notes/{id}")
    public ResponseEntity<Object> deleteClientNote(@PathVariable String id){
        boolean clientNotesToBeDeleted =clientNotesService.deletById(id);
        if (!clientNotesToBeDeleted){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Quote item deleted successfully",HttpStatus.NO_CONTENT,"",TimestampUtil.now());

    }

}
