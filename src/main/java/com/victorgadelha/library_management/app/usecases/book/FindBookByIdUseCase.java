package com.victorgadelha.library_management.app.usecases.book;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.repositories.BookRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class FindBookByIdUseCase {
    private final BookRepository repository;

    public FindBookByIdUseCase(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Book execute(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
    }
}