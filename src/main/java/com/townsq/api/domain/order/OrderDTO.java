package com.townsq.api.domain.order;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.townsq.api.domain.cart.CartItemDTO;
import com.townsq.api.domain.cart.CartItems;

import java.util.List;

public record OrderDTO(@JsonAlias("paymentType") String paymentType,
                       @JsonAlias("userId") Long userId,
                       @JsonAlias("items") List<CartItemDTO> cartItems) {
}
