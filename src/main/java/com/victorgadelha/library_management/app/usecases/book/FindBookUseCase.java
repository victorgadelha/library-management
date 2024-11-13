package com.victorgadelha.library_management.app.usecases.book;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.repositories.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FindBookUseCase {
    private final BookRepository repository;

    public FindBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Book execute(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
    }
}