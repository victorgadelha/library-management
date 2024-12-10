package com.victorgadelha.library_management.app.usecases.loan;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.domain.enums.LoanStatus;
import com.victorgadelha.library_management.domain.repositories.LoanRepository;
import com.victorgadelha.library_management.infra.mappers.LoanMapper;
import com.victorgadelha.library_management.web.dtos.loan.LoanDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ConfirmLoanReturnUseCase {
	private final FindLoanByIdUseCase findLoanByIdUseCase;
	private final LoanMapper loanMapper;
	private final LoanRepository loanRepository;

	public ConfirmLoanReturnUseCase(FindLoanByIdUseCase findLoanByIdUseCase, LoanMapper loanMapper,
			LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
		this.findLoanByIdUseCase = findLoanByIdUseCase;
		this.loanMapper = loanMapper;
	}

	public LoanDTO execute(UUID id) {
		var loan = loanRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Loan not found."));

		loan.setStatus(LoanStatus.RETURNED);
		loan.setReturnedAt(LocalDateTime.now());

		var updatedLoan = loanRepository.save(loan);
		return loanMapper.toDTO(updatedLoan);
	}
}