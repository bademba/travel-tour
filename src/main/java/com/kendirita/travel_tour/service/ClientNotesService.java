package com.kendirita.travel_tour.service;

import com.kendirita.travel_tour.dto.ClientNotesDTO;
import com.kendirita.travel_tour.entity.*;
import com.kendirita.travel_tour.repository.ClientNotesRepository;
import com.kendirita.travel_tour.repository.ClientRepository;
import com.kendirita.travel_tour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientNotesService {

    @Autowired
    private ClientNotesRepository clientNotesRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    public ClientNotes createClientNotes(ClientNotesDTO clientNotesDTO){
        ClientNotes clientNotes = new ClientNotes();

        Client client = clientRepository.findById(clientNotesDTO.getClientId()).orElseThrow(() ->
                new IllegalArgumentException("Client not found"));
        User user = userRepository.findById(clientNotesDTO.getCreatedBy()).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        clientNotes.setClient(client);
        clientNotes.setCreatedBy(user);
        clientNotes.setNoteType(clientNotesDTO.getNoteType());
        clientNotes.setContent(clientNotesDTO.getContent());
        clientNotes.setCreatedAt(clientNotesDTO.getCreatedAt());

        return clientNotesRepository.save(clientNotes);
    }

    public List<ClientNotes> listAllClientNotes(){
        return clientNotesRepository.findAll();
    }

    public ClientNotes searchById(String id){
        return clientNotesRepository.searchById(id);
    }

    public boolean deletById(String id){
        Optional<ClientNotes> clientNotes = Optional.ofNullable(clientNotesRepository.searchById(id));
        if (clientNotes.isEmpty()) {
            return false;
        }
        clientNotesRepository.delete(clientNotes.get());
        return true;
    }
}
