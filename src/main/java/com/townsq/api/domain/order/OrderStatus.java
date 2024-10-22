package com.townsq.api.domain.order;

public enum OrderStatus {

    PENDING("pending"),
    SUBMITTED("submitted");

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
}
