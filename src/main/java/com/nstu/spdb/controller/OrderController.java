package com.nstu.spdb.controller;

import com.nstu.spdb.dto.OrderDto;
import com.nstu.spdb.entity.Order;
import com.nstu.spdb.repository.OrderRepository;
import com.nstu.spdb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value = "order/getOrders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        List<OrderDto> orderDtos = new ArrayList<>(orders.size());
        orders.forEach(order -> {
            orderDtos.add(new OrderDto(order));

        });

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping(value = "order/getOrder", produces = "application/json", consumes = "application/json")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("orderId") Long orderId) {
        Order order = orderRepository.getOne(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new OrderDto(order), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @PostMapping(value = "order/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto));
    }
}
