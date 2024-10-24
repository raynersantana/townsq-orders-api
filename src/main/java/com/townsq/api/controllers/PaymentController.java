package com.townsq.api.controllers;

import com.townsq.api.domain.order.OrderDetailsDTO;
import com.townsq.api.domain.order.OrderDetailsPaymentDTO;
import com.townsq.api.domain.payment.PaymentDetailsDTO;
import com.townsq.api.domain.payment.PaymentProcessDTO;
import com.townsq.api.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOnePayment(@PathVariable Long id) {
        return paymentService.getOnePayment(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OrderDetailsPaymentDTO> processPayment(@RequestBody @Valid PaymentProcessDTO data) {
        return paymentService.processPayment(data);
    }
}
