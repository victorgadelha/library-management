package com.victorgadelha.library_management.app.usecases.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.enums.Role;
import com.victorgadelha.library_management.domain.repositories.UserRepository;
import com.victorgadelha.library_management.infra.mappers.UserMapper;
import com.victorgadelha.library_management.web.dtos.user.CreateUserRequestDTO;
import com.victorgadelha.library_management.web.dtos.user.CreateUserResponseDTO;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CreateUserUseCase(
            UserRepository userRepository,
            UserMapper userMapper,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional()
    public CreateUserResponseDTO execute(CreateUserRequestDTO userDTO) {
        var user = userMapper.toEntity(userDTO);

        user.setRole(Role.BASIC);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.password()));
        this.userRepository.save(user);
        System.out.println(user);

        return userMapper.toCreateUserResponseDTO(user);
    }
}
