package com.victorgadelha.libray_management.infra.adapters.gateway.repositories.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorgadelha.libray_management.domain.entities.User;

@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID> {
    
}
