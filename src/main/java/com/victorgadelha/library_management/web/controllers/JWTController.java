package com.victorgadelha.library_management.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.user.LoginUseCase;
import com.victorgadelha.library_management.web.dtos.jwt.JWTResponseDTO;
import com.victorgadelha.library_management.web.dtos.jwt.LoginRequestDTO;

@RestController
public class JWTController {
    private final LoginUseCase loginUseCase;

    public JWTController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("users/sign-in")
    public ResponseEntity<JWTResponseDTO> login(@RequestBody LoginRequestDTO userDTO) {

        var JWT = this.loginUseCase.execute(userDTO.email(), userDTO.password());
        return ResponseEntity.status(HttpStatus.OK).body(JWT);
    }
}
