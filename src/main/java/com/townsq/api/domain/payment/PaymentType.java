package com.townsq.api.domain.payment;

public enum PaymentType {

    CREDIT_CARD("credit_card"),
    PIX("pix"),
    PAYPAL("paypal"),
    BANK_TRANSFER("bank_transfer");


    PaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    private String paymentType;
}
