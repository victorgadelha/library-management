package com.victorgadelha.library_management.web.controllers;

import java.util.List;
import java.util.UUID;

import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.loan.UpdateLoanStatusUseCase;
import com.victorgadelha.library_management.app.usecases.user.FindAllUsersUseCase;
import com.victorgadelha.library_management.app.usecases.user.FindUserByIdUseCase;
import com.victorgadelha.library_management.web.dtos.loan.LoanDTO;
import com.victorgadelha.library_management.web.dtos.loan.UpdateLoanStatusRequestDTO;
import com.victorgadelha.library_management.web.dtos.user.UserDTO;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final FindUserByIdUseCase findUserByIdUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final UpdateLoanStatusUseCase updateLoanStatusUseCase;

    public AdminController(FindUserByIdUseCase findUserByIdUseCase, FindAllUsersUseCase findAllUsersUseCase,
            UpdateLoanStatusUseCase updateLoanStatusUseCase) {
        this.findAllUsersUseCase = findAllUsersUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.updateLoanStatusUseCase = updateLoanStatusUseCase;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        var user = this.findUserByIdUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        var users = this.findAllUsersUseCase.execute();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PatchMapping("/loans/{id}/status")
    public ResponseEntity<LoanDTO> updateLoanStatus(
            @PathVariable UUID id,
            @RequestBody UpdateLoanStatusRequestDTO updateLoanStatusRequestDTO) {
                
        var loan = this.updateLoanStatusUseCase.execute(updateLoanStatusRequestDTO, id);

        return ResponseEntity.status(HttpStatus.OK).body(loan);
    }

}
