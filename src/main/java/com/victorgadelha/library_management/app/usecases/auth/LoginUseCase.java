package com.victorgadelha.library_management.app.usecases.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.app.usecases.user.FindUserByEmailUseCase;
import com.victorgadelha.library_management.app.usecases.user.FindUserCredentialsByEmailUseCase;
import com.victorgadelha.library_management.infra.jwt.JWTService;
import com.victorgadelha.library_management.web.dtos.auth.JWTLoginResponseDTO;

@Service
public class LoginUseCase {

    private final FindUserCredentialsByEmailUseCase findUserCredentialsByEmailUseCase;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginUseCase(
            FindUserByEmailUseCase findUserByEmailUseCase,
            JWTService jwtService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            FindUserCredentialsByEmailUseCase findUserCredentialsByEmailUseCase) {
        this.findUserCredentialsByEmailUseCase = findUserCredentialsByEmailUseCase;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    public JWTLoginResponseDTO execute(String email, String password) {

        var userCredentials = this.findUserCredentialsByEmailUseCase.execute(email);

        if (!this.bCryptPasswordEncoder.matches(password, userCredentials.password())
                || !userCredentials.email().equals(email)) {
            throw new IllegalArgumentException("Invalid credentials.");
        }
        return this.jwtService.generateToken(email);
    }
}