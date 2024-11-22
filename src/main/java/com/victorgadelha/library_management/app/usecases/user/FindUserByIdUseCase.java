package com.victorgadelha.library_management.app.usecases.user;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.repositories.UserRepository;
import com.victorgadelha.library_management.infra.mappers.UserMapper;
import com.victorgadelha.library_management.web.dtos.user.UserDTO;

@Service
public class FindUserByIdUseCase {

    public FindUserByIdUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserDTO execute(UUID id) {
        var user = this.userRepository.findById(id);

        return this.userMapper.toDTO(user.get());
    }
}
