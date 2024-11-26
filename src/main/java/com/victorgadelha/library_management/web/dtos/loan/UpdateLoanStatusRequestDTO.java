package com.victorgadelha.library_management.web.dtos.loan;

import com.victorgadelha.library_management.domain.enums.LoanStatus;

public record UpdateLoanStatusRequestDTO(LoanStatus status) {
    
}
