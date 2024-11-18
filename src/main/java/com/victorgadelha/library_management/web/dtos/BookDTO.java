package com.victorgadelha.library_management.web.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookDTO(UUID id, String isbn, String title, String author, String languages, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}