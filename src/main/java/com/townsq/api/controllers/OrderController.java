package com.townsq.api.controllers;

import com.townsq.api.domain.order.Order;
import com.townsq.api.domain.order.OrderDTO;
import com.townsq.api.repositories.OrderRepository;
import com.townsq.api.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("orders")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createNewOrder(@RequestBody OrderDTO data) {
        return orderService.addOrder(data);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders(@RequestHeader (name = "Authorization") String token) {
        return orderService.getAllOrders(token.replace("Bearer ", ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneOrder(@PathVariable("id") Long id) {
        return orderService.getOneOrder(id);
    }
}