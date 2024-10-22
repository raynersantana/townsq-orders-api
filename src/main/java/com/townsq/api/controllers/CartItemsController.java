package com.townsq.api.controllers;

import com.townsq.api.domain.cart.CartItemDTO;
import com.townsq.api.domain.cart.CartItems;
import com.townsq.api.domain.order.Order;
import com.townsq.api.domain.order.OrderStatus;
import com.townsq.api.domain.product.Product;
import com.townsq.api.domain.user.User;
import com.townsq.api.repositories.CartItemsRepository;
import com.townsq.api.repositories.OrderRepository;
import com.townsq.api.repositories.ProductRepository;
import com.townsq.api.repositories.UserRepository;
import com.townsq.api.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemsController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody CartItemDTO cartItemDTO) {
        return cartItemService.addProductToCart(cartItemDTO);
    }
}
