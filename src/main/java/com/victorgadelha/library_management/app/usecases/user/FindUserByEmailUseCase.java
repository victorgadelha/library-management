package com.victorgadelha.library_management.app.usecases.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.repositories.UserRepository;
import com.victorgadelha.library_management.infra.mappers.UserMapper;
import com.victorgadelha.library_management.web.dtos.user.UserDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FindUserByEmailUseCase {
    public FindUserByEmailUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserDTO execute(String email) {
        var user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        return this.userMapper.toDTO(user);
    }
}
