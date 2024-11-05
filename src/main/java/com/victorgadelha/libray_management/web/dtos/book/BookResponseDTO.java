package com.victorgadelha.libray_management.web.dtos.book;


import java.time.LocalDateTime;
import java.util.UUID;

public record BookResponseDTO(UUID id, String isbn, String title, String author, String languages,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
}
