package com.victorgadelha.library_management.web.dtos.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.victorgadelha.library_management.domain.entities.Loan;
import com.victorgadelha.library_management.domain.entities.Reservation;
import com.victorgadelha.library_management.domain.enums.UserRole;

public record UserProfileDTO(
        UUID id,
        String name,
        String email,
        UserRole role,
        List<Loan> loans,
        List<Reservation> reservations,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
