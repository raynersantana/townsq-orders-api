package com.townsq.api.domain.payment;

public record PaymentDetailsDTO(Long id, Long orderId, Long userId, PaymentType paymentType) {
}
