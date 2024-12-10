package com.victorgadelha.library_management.app.usecases.user;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.repositories.UserRepository;
import com.victorgadelha.library_management.infra.mappers.UserMapper;
import com.victorgadelha.library_management.web.dtos.user.UserDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FindUserByIdUseCase {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public FindUserByIdUseCase(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Transactional(readOnly = true)
	public UserDTO execute(UUID id) {
		var user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));

		return this.userMapper.toDTO(user);
	}
}
