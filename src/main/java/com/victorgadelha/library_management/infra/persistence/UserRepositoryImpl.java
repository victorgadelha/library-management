package com.victorgadelha.library_management.infra.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.victorgadelha.library_management.domain.entities.User;
import com.victorgadelha.library_management.domain.repositories.UserRepository;
import com.victorgadelha.library_management.infra.repositories.JpaUserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> findAll() {
        return this.jpaUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.jpaUserRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return this.jpaUserRepository.save(user);
    }

    @Override
    public User updateById(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    @Override
    public void deleteById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.jpaUserRepository.findByEmail(email);
    }

}
