package com.victorgadelha.library_management.app.usecases.book;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.domain.repositories.BookRepository;

@Service
public class DeleteBookUseCase {
    private final BookRepository repository;

    public DeleteBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        this.repository.deleteById(id);
    }
}