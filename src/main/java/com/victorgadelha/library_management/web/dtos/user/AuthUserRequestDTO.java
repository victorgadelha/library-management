package com.victorgadelha.library_management.web.dtos.user;

public record AuthUserRequestDTO(
        String email,
        String password) {
}
