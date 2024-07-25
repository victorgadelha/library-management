package com.victorgadelha.libray_management.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookDTO(UUID id, String isbn, String title, String author, String languages, LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}