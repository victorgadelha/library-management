package com.victorgadelha.library_management.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.loan.CreateLoanUseCase;
import com.victorgadelha.library_management.web.dtos.loan.CreateLoanRequestDTO;
import com.victorgadelha.library_management.web.dtos.loan.LoanDTO;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final CreateLoanUseCase createLoanUseCase;

    public LoanController(CreateLoanUseCase createLoanUseCase) {
        this.createLoanUseCase = createLoanUseCase;
    }

    @PostMapping()
    public ResponseEntity<LoanDTO> createLoan(@RequestBody CreateLoanRequestDTO createLoanRequestDTO) {
        var loan = this.createLoanUseCase.execute(createLoanRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }
}
