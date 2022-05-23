package com.nstu.spdb.controller;

import com.nstu.spdb.dto.ClientDto;
import com.nstu.spdb.entity.Client;
import com.nstu.spdb.repository.ClientRepository;
import com.nstu.spdb.service.ClientService;
import com.nstu.spdb.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private JsonUtils jsonUtils;

    @GetMapping(value = "client/getClients")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        List<ClientDto> clientDtos = new ArrayList<>(clients.size());
        clients.forEach(order -> clientDtos.add(new ClientDto(order)));

        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }

    @GetMapping(value = "client/getClient", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ClientDto> getClient(@PathVariable("clientId") Long clientId) {
        Client client = clientRepository.getOne(clientId);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ClientDto(client), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @PostMapping(value = "client/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createClient(@RequestBody ClientDto clientDto) {
        /*if (clientDtoJson == null) {
            return null;
        }
        final ClientDto clientDto = jsonUtils.fromJson(clientDtoJson, ClientDto.class);*/

        if (StringUtils.isBlank(clientDto.getFullName())) {
            return null;
        }

        clientRepository.save(clientService.createFromDto(clientDto));
        return new ResponseEntity<>("Client be create!", HttpStatus.OK);
    }
}
