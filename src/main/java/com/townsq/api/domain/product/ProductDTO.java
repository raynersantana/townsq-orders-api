package com.townsq.api.domain.product;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotEmpty(message = "The name is empty or not valid")
        String name,

        @NotNull
        String description,

        @NotNull(message = "The price is empty or not valid")
        @Digits(message = "price must be a number!", integer = 4, fraction = 0)
        Integer price) {
}
