package com.victorgadelha.library_management.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.user.CreateUserUseCase;
import com.victorgadelha.library_management.domain.entities.User;
import com.victorgadelha.library_management.web.dtos.CreateUserResponseDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody User user) {
        var newUser = this.createUserUseCase.execute(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
