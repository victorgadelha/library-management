package com.victorgadelha.library_management.web.dtos.user;

import java.time.LocalDateTime;
import java.util.UUID;

import com.victorgadelha.library_management.domain.enums.UserType;

public record UserDTO(
        UUID id,
        String name,
        String email,
        UserType type,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
