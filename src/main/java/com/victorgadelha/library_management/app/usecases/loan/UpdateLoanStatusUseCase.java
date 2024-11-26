package com.victorgadelha.library_management.app.usecases.loan;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.domain.repositories.LoanRepository;
import com.victorgadelha.library_management.infra.mappers.LoanMapper;
import com.victorgadelha.library_management.web.dtos.loan.LoanDTO;
import com.victorgadelha.library_management.web.dtos.loan.UpdateLoanStatusRequestDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UpdateLoanStatusUseCase {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final FindLoanByIdUseCase findLoanByIdUseCase;

    public UpdateLoanStatusUseCase(LoanRepository loanRepository, LoanMapper loanMapper,
            FindLoanByIdUseCase findLoanByIdUseCase) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.findLoanByIdUseCase = findLoanByIdUseCase;
    }

    public LoanDTO execute(UpdateLoanStatusRequestDTO updateLoanStatusRequestDTO, UUID id) {

        var loan = this.loanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Loan not found."));

        loan.changeStatus(updateLoanStatusRequestDTO.status());

        this.loanRepository.save(loan);

        return this.loanMapper.toDTO(loan);
    }
}
