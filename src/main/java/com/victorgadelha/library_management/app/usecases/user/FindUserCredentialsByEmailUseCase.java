package com.victorgadelha.library_management.app.usecases.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.repositories.UserRepository;
import com.victorgadelha.library_management.infra.mappers.UserMapper;
import com.victorgadelha.library_management.web.dtos.user.AuthUserRequestDTO;

@Service
public class FindUserCredentialsByEmailUseCase {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public FindUserCredentialsByEmailUseCase(
            UserMapper userMapper,
            UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public AuthUserRequestDTO execute(String email) {
        var user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        return this.userMapper.toAuthUserRequestDTO(user);
    }
}
