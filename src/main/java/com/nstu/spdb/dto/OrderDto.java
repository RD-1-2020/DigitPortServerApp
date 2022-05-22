package com.nstu.spdb.dto;

import com.nstu.spdb.entity.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto implements Serializable {
    private Long orderId;
    private ClientDto client;
    private Date createDate;
    private Date closeDate;
    private List<CargoDto> cargos;

    public OrderDto(Order order) {
        if (order == null) {
            return;
        }

        this.orderId = order.getId();
        this.client = new ClientDto(order.getClient());
        this.createDate = order.getCreateDate();
        this.closeDate = order.getCloseDate();

        order.getCargos().forEach(cargo -> {
            getCargos().add(new CargoDto(cargo));
        });
    }

    public Long getOrderId() {
        return orderId;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public List<CargoDto> getCargos() {
        if (cargos == null) {
            cargos = new ArrayList<>();
        }

        return cargos;
    }

    public void setCargos(List<CargoDto> cargos) {
        this.cargos = cargos;
    }
}
