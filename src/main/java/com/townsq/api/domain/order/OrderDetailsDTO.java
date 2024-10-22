package com.townsq.api.domain.order;

import com.townsq.api.domain.cart.CartItemDTO;
import com.townsq.api.domain.cart.CartItems;

import java.util.List;

public record OrderDetailsDTO (Long id,
                              String paymentStatus,
                              List<CartItems> cartItem,
                               Long totalPrice){
    public OrderDetailsDTO(Order order) {
        this(order.getId(), order.getPaymentType(), order.getCartItems(), order.getTotalPrice());
    }
}
