package com.townsq.api.domain.cart;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private Long price;
    private Long quantity;
    private Long productId;
    private Long orderId;
    private String productName;
    private Long userId;
}
