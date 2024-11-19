package com.victorgadelha.library_management.infra.mappers;

import org.mapstruct.Mapper;

import com.victorgadelha.library_management.domain.entities.User;
import com.victorgadelha.library_management.web.dtos.user.CreateUserRequestDTO;
import com.victorgadelha.library_management.web.dtos.user.CreateUserResponseDTO;
import com.victorgadelha.library_management.web.dtos.user.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    // // Mapeamento de DTO para entidade
    User toEntity(CreateUserRequestDTO createUserRequestDTO);

    CreateUserResponseDTO toCreateUserResponseDTO(User user);

}
