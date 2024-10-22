package com.townsq.api.domain.user;

public record RegisterDTO(String username, String password, UserRole role) {
}
