package com.victorgadelha.library_management.app.usecases.user;

import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.domain.entities.User;
import com.victorgadelha.library_management.domain.enums.UserType;
import com.victorgadelha.library_management.domain.repositories.UserRepository;
import com.victorgadelha.library_management.web.dtos.CreateUserResponseDTO;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserResponseDTO execute(User user) {

        var newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setType(UserType.BASIC);

        this.userRepository.save(newUser);
        var userDTO = new CreateUserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                UserType.BASIC);

        return userDTO;
    }
}
