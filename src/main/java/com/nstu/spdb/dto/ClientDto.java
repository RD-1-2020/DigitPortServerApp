package com.nstu.spdb.dto;

import com.nstu.spdb.entity.Client;

import java.io.Serializable;

public class ClientDto implements Serializable {
    private Long id;
    private String fullName;

    public ClientDto(Client client) {
        if (client == null) {
            return;
        }

        this.id = client.getId();
        this.fullName = client.getFullName();
    }

    public ClientDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
