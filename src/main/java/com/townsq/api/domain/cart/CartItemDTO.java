package com.townsq.api.domain.cart;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private Long price;
    @NotNull(message = "quantity can't be null")
    @Digits(message = "quantity must be a number!", integer = 0, fraction = 0)
    private Long quantity;
    @NotNull(message = "productId can't be null")
    @Digits(message = "productId must be a number!", integer = 0, fraction = 0)
    private Long productId;
    private Long orderId;
    private String productName;
    private Long userId;
}
