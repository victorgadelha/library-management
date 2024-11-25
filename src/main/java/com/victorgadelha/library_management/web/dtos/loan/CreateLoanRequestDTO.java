package com.victorgadelha.library_management.web.dtos.loan;

import java.util.UUID;

public record CreateLoanRequestDTO(
                UUID userId,
                UUID bookId) {
}
