package com.victorgadelha.library_management.web.dtos.jwt;

public record JWTResponseDTO(String token, long expiresIn) {
}