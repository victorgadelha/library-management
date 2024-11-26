package com.victorgadelha.library_management.web.dtos.loan;

import java.time.LocalDateTime;
import java.util.UUID;

import com.victorgadelha.library_management.domain.enums.LoanStatus;

public record LoanDTO(
                UUID id,
                LocalDateTime createdAt, 
                LocalDateTime updatedAt,
                LocalDateTime approvedAt,
                LocalDateTime returnedAt,
                LoanStatus status,
                UUID bookId,
                UUID userId) {
}
