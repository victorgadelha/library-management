package com.victorgadelha.library_management.app.usecases.loan;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.domain.repositories.LoanRepository;
import com.victorgadelha.library_management.infra.mappers.LoanMapper;
import com.victorgadelha.library_management.web.dtos.loan.LoanDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FindLoanByIdUseCase {
	private final LoanRepository loanRepository;
	private final LoanMapper loanMapper;

	public FindLoanByIdUseCase(LoanRepository loanRepository, LoanMapper loanMapper) {
		this.loanRepository = loanRepository;
		this.loanMapper = loanMapper;
	}

	public LoanDTO execute(UUID id) {
		return loanMapper.toDTO(
				this.loanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Loan not found.")));
	}
}