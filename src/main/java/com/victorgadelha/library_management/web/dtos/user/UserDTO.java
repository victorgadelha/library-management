package com.victorgadelha.library_management.web.dtos.user;

import java.time.LocalDateTime;
import java.util.UUID;

import com.victorgadelha.library_management.domain.enums.UserRole;

public record UserDTO(
        UUID id,
        String name,
        String email,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
