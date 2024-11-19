package com.victorgadelha.library_management.app.usecases.user;

import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {
    
    private final FindUserUseCase findUserUseCase;

    public LoginUseCase(FindUserUseCase findUserUseCase) {
        this.findUserUseCase = findUserUseCase;
    }

    
}
