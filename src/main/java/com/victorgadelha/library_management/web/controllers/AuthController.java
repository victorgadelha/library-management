package com.victorgadelha.library_management.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.auth.LoginUseCase;
import com.victorgadelha.library_management.app.usecases.user.CreateUserUseCase;
import com.victorgadelha.library_management.web.dtos.auth.JWTLoginResponseDTO;
import com.victorgadelha.library_management.web.dtos.auth.JWTRequestLoginDTO;
import com.victorgadelha.library_management.web.dtos.user.CreateUserRequestDTO;
import com.victorgadelha.library_management.web.dtos.user.CreateUserResponseDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final LoginUseCase loginUseCase;
	private final CreateUserUseCase createUserUseCase;

	public AuthController(LoginUseCase loginUseCase, CreateUserUseCase createUserUseCase) {
		this.createUserUseCase = createUserUseCase;
		this.loginUseCase = loginUseCase;
	}

	@PostMapping("/sign-in")
	public ResponseEntity<JWTLoginResponseDTO> login(@RequestBody JWTRequestLoginDTO userDTO) {
		var JWT = this.loginUseCase.execute(userDTO.email(), userDTO.password());

		return ResponseEntity.status(HttpStatus.OK).body(JWT);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO userDTO) {
		var user = this.createUserUseCase.execute(userDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

}
