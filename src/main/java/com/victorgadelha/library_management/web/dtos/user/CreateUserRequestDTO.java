package com.victorgadelha.library_management.web.dtos.user;


public record CreateUserRequestDTO(
        String name,
        String email,
        String password) {
}
