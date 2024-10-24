package com.townsq.api.controllers;

import com.townsq.api.domain.order.Order;
import com.townsq.api.domain.order.OrderDTO;
import com.townsq.api.domain.order.OrderDetailsDTO;
import com.townsq.api.repositories.OrderRepository;
import com.townsq.api.services.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("orders")
@RestController
@SecurityRequirement(name = "bearer-key")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createNewOrder(@RequestBody @Valid OrderDTO data) {
        return orderService.addOrder(data);
    }

    @GetMapping
    public ResponseEntity getAllOrders(@RequestHeader (name = "Authorization") String token) {
        return orderService.getAllOrders(token.replace("Bearer ", ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneOrder(@PathVariable("id") Long id) {
        return orderService.getOneOrder(id);
    }
}