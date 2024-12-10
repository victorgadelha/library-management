package com.victorgadelha.library_management.web.controllers.admin;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.user.FindAllUsersUseCase;
import com.victorgadelha.library_management.app.usecases.user.FindUserByIdUseCase;
import com.victorgadelha.library_management.web.dtos.user.UserDTO;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

	private final FindUserByIdUseCase findUserByIdUseCase;
	private final FindAllUsersUseCase findAllUsersUseCase;

	public AdminUserController(FindUserByIdUseCase findUserByIdUseCase, FindAllUsersUseCase findAllUsersUseCase) {
		this.findAllUsersUseCase = findAllUsersUseCase;
		this.findUserByIdUseCase = findUserByIdUseCase;

	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> findAllUsers() {
		var users = this.findAllUsersUseCase.execute();

		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
		var user = this.findUserByIdUseCase.execute(id);

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

}
