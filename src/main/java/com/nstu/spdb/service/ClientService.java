package com.nstu.spdb.service;

import com.nstu.spdb.dto.ClientDto;
import com.nstu.spdb.entity.Client;
import com.nstu.spdb.repository.ClientRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    public Client createFromDto(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }

        Client client = new Client();
        client.setFullName(clientDto.getFullName());
        client.setId(client.getId());

        return client;
    }

    @Nullable
    public Client createClient(String fullName) {
        if((StringUtils.isBlank(fullName))) {
            return null;
        }

        logger.info("create client with name '{}' in process", fullName);
        Client client = new Client();
        client.setFullName(fullName);

        return clientRepository.save(client);
    }
}
