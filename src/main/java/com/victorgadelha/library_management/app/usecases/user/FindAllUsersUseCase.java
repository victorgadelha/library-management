package com.victorgadelha.library_management.app.usecases.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.repositories.UserRepository;
import com.victorgadelha.library_management.infra.mappers.UserMapper;
import com.victorgadelha.library_management.web.dtos.user.UserDTO;

@Service
public class FindAllUsersUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public FindAllUsersUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> execute() {

        return this.userRepository.findAll()
                .stream()
                .map(user -> userMapper.toDTO(user))
                .collect(Collectors.toList());
    }
}