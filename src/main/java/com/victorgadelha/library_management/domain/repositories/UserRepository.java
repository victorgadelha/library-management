package com.victorgadelha.library_management.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.victorgadelha.library_management.domain.entities.User;

public interface UserRepository {
    public List<User> findAll();

    public Optional<User> findById(UUID id);

    public User save(User user);

    public User updateById(User user);

    public void deleteById(UUID id);
}
