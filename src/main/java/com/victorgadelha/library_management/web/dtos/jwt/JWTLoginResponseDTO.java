package com.victorgadelha.library_management.web.dtos.jwt;

public record JWTLoginResponseDTO(String token, long expiresIn) {
}