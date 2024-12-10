package com.victorgadelha.library_management.web.controllers.admin;

import java.util.UUID;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.loan.ConfirmLoanReturnUseCase;
import com.victorgadelha.library_management.web.dtos.loan.LoanDTO;

@RestController
@RequestMapping("/admin/loans")
public class AdminLoanController {
	private final ConfirmLoanReturnUseCase registerBookReturnUseCase;

	public AdminLoanController(ConfirmLoanReturnUseCase registerBookReturnUseCase) {
		this.registerBookReturnUseCase = registerBookReturnUseCase;
	}

	@PatchMapping("{id}/confirm-return")
	public LoanDTO confirmLoanReturn(@PathVariable UUID id) {
		return this.registerBookReturnUseCase.execute(id);
	}
}
