package com.victorgadelha.library_management.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.user.CreateUserUseCase;
import com.victorgadelha.library_management.app.usecases.user.FindUserProfileUseCase;
import com.victorgadelha.library_management.web.dtos.user.CreateUserRequestDTO;
import com.victorgadelha.library_management.web.dtos.user.CreateUserResponseDTO;
import com.victorgadelha.library_management.web.dtos.user.UserProfileDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final FindUserProfileUseCase findUserProfileUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            FindUserProfileUseCase findUserProfileUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.findUserProfileUseCase = findUserProfileUseCase;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO userDTO) {
        var user = this.createUserUseCase.execute(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/me"   )
    public ResponseEntity<UserProfileDTO> getUserDetails(@RequestHeader("Authorization") String token) {
        var user = this.findUserProfileUseCase.execute(token);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
