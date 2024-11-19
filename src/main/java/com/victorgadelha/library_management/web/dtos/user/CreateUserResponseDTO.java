package com.victorgadelha.library_management.web.dtos.user;

import java.util.UUID;

import com.victorgadelha.library_management.domain.enums.UserType;

public record CreateUserResponseDTO(UUID id, String name, String email, UserType type) {
    
}
