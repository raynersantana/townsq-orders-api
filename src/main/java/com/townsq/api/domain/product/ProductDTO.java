package com.townsq.api.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(String name, @NotNull String description,
                         @NotNull(message = "Please, send me a number for price") Integer price) {
}
