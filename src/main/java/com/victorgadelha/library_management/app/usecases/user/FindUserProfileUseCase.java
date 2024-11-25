package com.victorgadelha.library_management.app.usecases.user;

import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.domain.repositories.UserRepository;
import com.victorgadelha.library_management.infra.mappers.UserMapper;
import com.victorgadelha.library_management.web.dtos.user.UserProfileDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FindUserProfileUseCase {

    private final JwtDecoder jwtDecoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public FindUserProfileUseCase(JwtDecoder jwtDecoder, UserRepository userRepository, UserMapper userMapper) {
        this.jwtDecoder = jwtDecoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserProfileDTO execute(String authorization) {
        var token = authorization.replace("Bearer ", "");
        var userId = jwtDecoder.decode(token).getSubject();
        var user = this.userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        return userMapper.toUserProfileDTO(user);
    }
}
