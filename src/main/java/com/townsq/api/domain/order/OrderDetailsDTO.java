package com.townsq.api.domain.order;

import com.townsq.api.domain.cart.CartItemDTO;
import com.townsq.api.domain.cart.CartItems;

import java.util.List;

public record OrderDetailsDTO (Long id,
                              List<CartItems> cartItem,
                               Long totalPrice){
    public OrderDetailsDTO(Order order) {
        this(order.getId(), order.getCartItems(), order.getTotalPrice());
    }
}
