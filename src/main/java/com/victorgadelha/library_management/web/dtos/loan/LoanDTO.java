package com.victorgadelha.library_management.web.dtos.loan;

import java.time.LocalDateTime;
import java.util.UUID;

import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.entities.User;
import com.victorgadelha.library_management.domain.enums.LoanStatus;

public record LoanDTO(
        UUID id,
        LocalDateTime loanDate,
        LocalDateTime updatedAt,
        LocalDateTime returnDate,
        LoanStatus status,
        Book book,
        User user) {
}
