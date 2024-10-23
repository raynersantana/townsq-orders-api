package com.townsq.api.services;

import com.townsq.api.config.security.TokenService;
import com.townsq.api.domain.cart.CartItemDTO;
import com.townsq.api.domain.cart.CartItems;
import com.townsq.api.domain.order.*;
import com.townsq.api.domain.payment.Payment;
import com.townsq.api.domain.payment.PaymentStatus;
import com.townsq.api.domain.product.Product;
import com.townsq.api.domain.user.User;
import com.townsq.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    PaymentRepository paymentRepository;

    public ResponseEntity addOrder(OrderDTO data) {
        Optional<User> optionalUser = userRepository.findById(data.userId());
        if(optionalUser.isPresent()) {
            Order order = new Order();
            order.setUser(optionalUser.get());
            order.setOrderStatus(OrderStatus.SUBMITTED);
            var orderSaved = orderRepository.save(order);

            Payment payment = new Payment(orderSaved, optionalUser.get(), data.paymentType(), PaymentStatus.PENDING);
            paymentRepository.save(payment);

            List<CartItemDTO> cartItems = data.cartItems();
            for(CartItemDTO item : cartItems) {
                if(item.getProductId() != null) {
                    Optional<Product> optionalProduct = productRepository.findById(item.getProductId());
                    if(optionalProduct.isPresent()) {
                        Product product = optionalProduct.get();
                        Long priceCalculated = product.getPrice() * item.getQuantity();

                        order.setTotalPrice(order.getTotalPrice() == null ? priceCalculated : order.getTotalPrice() + priceCalculated);

                        CartItems cartItem = new CartItems();
                        cartItem.setProduct(product);
                        cartItem.setUser(optionalUser.get());
                        cartItem.setQuantity(item.getQuantity());
                        cartItem.setOrder(orderSaved);
                        cartItem.setPrice(product.getPrice());
                        cartItemsRepository.save(cartItem);
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return null;
    }

    public ResponseEntity<?> getAllOrders(String token) {
        var username = tokenService.validateToken(token);
        if(username != null) {
            List<OrderDetailsDTO> orders = orderRepository.findByUsername(username).stream()
                    .map(order -> new OrderDetailsDTO(order.getId(), order.getCartItems(), order.getTotalPrice())).toList();
            if(!orders.isEmpty()) {
                return ResponseEntity.ok().body(orders);
            }
        }
        return null;
    }

    public ResponseEntity<?> getOneOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.ok().body(new OrderDetailsDTO(order.getId(), order.getCartItems(), order.getTotalPrice()));
        }
        return null;
    }
}