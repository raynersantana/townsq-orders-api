package com.townsq.api.services;

import com.townsq.api.domain.order.Order;
import com.townsq.api.domain.order.OrderDetailsDTO;
import com.townsq.api.domain.order.OrderDetailsPaymentDTO;
import com.townsq.api.domain.order.OrderStatus;
import com.townsq.api.domain.payment.*;
import com.townsq.api.repositories.OrderRepository;
import com.townsq.api.repositories.PaymentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<?> getOnePayment(Long id) {

        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if(optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO(
                    payment.getId(),
                    payment.getOrder().getId(),
                    payment.getUser().getId(),
                    payment.getPaymentType());
            return ResponseEntity.ok().body(paymentDetailsDTO);
        }

        PaymentEditDTO paymentEditDTO = new PaymentEditDTO("Payment not found!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(paymentEditDTO);

    }

    public ResponseEntity<OrderDetailsPaymentDTO> processPayment(PaymentProcessDTO data) {
        Optional<Payment> optionalPayment = paymentRepository.findById(data.id());
        if(optionalPayment.isPresent() && !optionalPayment.get().getPaymentStatus().equals(PaymentStatus.DONE)) {
            Payment payment = optionalPayment.get();

            Optional<Order> optionalOrder = orderRepository.findById(payment.getOrder().getId());
            if(optionalOrder.isPresent() && !optionalOrder.get().getOrderStatus().equals(OrderStatus.DONE)) {

                Order order = new Order(
                        optionalOrder.get().getId(),
                        optionalOrder.get().getTotalPrice(),
                        OrderStatus.DONE,
                        optionalOrder.get().getUser());
                var savedOrder = orderRepository.save(order);

                payment.setPaymentStatus(PaymentStatus.DONE);
                paymentRepository.save(payment);

                OrderDetailsPaymentDTO orderDetailsPaymentDTO = new OrderDetailsPaymentDTO(
                        savedOrder.getId(),
                        savedOrder.getOrderStatus());

                return ResponseEntity.ok().body(orderDetailsPaymentDTO);
            }
        }

        return null;
    }
}
