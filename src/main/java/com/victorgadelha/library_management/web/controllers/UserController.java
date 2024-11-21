package com.victorgadelha.library_management.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.user.CreateUserUseCase;
import com.victorgadelha.library_management.web.dtos.user.CreateUserRequestDTO;
import com.victorgadelha.library_management.web.dtos.user.CreateUserResponseDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;


    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO userDTO) {

        var user = this.createUserUseCase.execute(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
