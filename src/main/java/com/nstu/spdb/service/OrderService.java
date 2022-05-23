package com.nstu.spdb.service;

import com.nstu.spdb.dto.CargoDto;
import com.nstu.spdb.dto.OrderDto;
import com.nstu.spdb.entity.Cargo;
import com.nstu.spdb.entity.Client;
import com.nstu.spdb.entity.Order;
import com.nstu.spdb.enums.OrderStatus;
import com.nstu.spdb.repository.ClientRepository;
import com.nstu.spdb.repository.OrderRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CargoService cargoService;

    public Order createFromDto(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }

        Order order = new Order();
        order.setCreateDate(orderDto.getCreateDate());
        order.setClient(clientService.createFromDto(orderDto.getClient()));
        order.setCloseDate(orderDto.getCloseDate());
        order.setId(orderDto.getOrderId());

        final List<CargoDto> cargos = orderDto.getCargos();
        final List<Cargo> orderCargos = order.getCargos();
        if (cargos != null) {
            cargos.forEach(cargoDto -> {
                orderCargos.add(cargoService.createFromDto(cargoDto));
            });
        }

        return order;
    }

    public HttpStatus createOrder(OrderDto orderDto) {
        Client client = clientService.createFromDto(orderDto.getClient());
        if (client == null || StringUtils.isBlank(client.getFullName())) {
            return HttpStatus.NOT_FOUND;
        }

        final String formattedName = client.getFullName() == null ? client.getFullName() : client.getFullName().trim();
        final List<Client> clients = clientRepository.getClientsByFullName(formattedName);
        if (clients == null || clients.isEmpty()) {
            client = clientService.createClient(formattedName);
        } else {
            client = clients.get(0);
        }

        Order order = new Order();

        final List<Cargo> cargoList = cargoService.createFromDto(orderDto.getCargos());
        List<Cargo> cargos = cargoService.createCargos(cargoList);
        if (cargos == null) {
            return HttpStatus.BAD_REQUEST;
        }

        order.getCargos().addAll(cargos);
        order.setClient(client);
        order.setCreateDate(new Date());
        order.setOrderStatus(OrderStatus.IN_WORK);

        orderRepository.saveAndFlush(order);

        return HttpStatus.OK;
    }

    public void addCargoToOrder(Long orderId, Long clientId) {
        final Order order = orderRepository.getOne(orderId);

        order.setClient(clientRepository.getOne(clientId));

        orderRepository.save(order);
    }
}
