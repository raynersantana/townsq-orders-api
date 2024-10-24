package com.townsq.api.domain.user;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @NotEmpty(message = "username can't be empty!") String username,
        @NotEmpty(message = "password can't be empty!") String password) {
}
