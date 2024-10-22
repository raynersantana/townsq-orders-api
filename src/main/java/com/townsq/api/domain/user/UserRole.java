package com.townsq.api.domain.user;

public enum UserRole {

    ADMIN("admin"),
    ACCOUNT_MANAGER("account_manager"),
    DEFAULT("default");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
