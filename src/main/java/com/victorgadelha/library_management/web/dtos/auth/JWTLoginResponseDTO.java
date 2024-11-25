package com.victorgadelha.library_management.web.dtos.auth;

public record JWTLoginResponseDTO(String token, long expiresIn) {
}