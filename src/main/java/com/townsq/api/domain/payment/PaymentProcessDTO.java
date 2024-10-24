package com.townsq.api.domain.payment;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record PaymentProcessDTO(@NotNull(message = "id can't be null!")
                                @Digits(integer = 3, fraction = 0, message = "id should be a number!")
                                Long id) {
}
