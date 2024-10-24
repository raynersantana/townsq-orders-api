package com.townsq.api.domain.order;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.townsq.api.domain.cart.CartItemDTO;
import com.townsq.api.domain.cart.CartItems;
import com.townsq.api.domain.payment.PaymentType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDTO(@NotNull(message = "paymentType can't be empty") @JsonAlias("paymentType") PaymentType paymentType,
                       @NotNull(message = "userId can't be null") @JsonAlias("userId") Long userId,
                       @NotEmpty(message = "items can't be empty") @JsonAlias("items") List<CartItemDTO> cartItems) {
}
