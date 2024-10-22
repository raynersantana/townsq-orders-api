package com.townsq.api.domain.order;

import com.townsq.api.domain.cart.ReturningCarItemDTO;

import java.util.List;


public record ReturnOrderDTO(Long totalPrice, List<ReturningCarItemDTO> cartItems) {
}
