package com.townsq.api.domain.user;

import jakarta.validation.constraints.NotNull;

public record UserEditDTO(String username,
                          @NotNull(message = "Role is not optional") UserRole role) {
}
