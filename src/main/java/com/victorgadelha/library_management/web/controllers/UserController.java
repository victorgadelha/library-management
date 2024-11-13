package com.victorgadelha.library_management.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.domain.entities.User;
import com.victorgadelha.library_management.infra.adapters.gateway.repositories.user.JpaUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    JpaUserRepository jpaUserRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        this.jpaUserRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
