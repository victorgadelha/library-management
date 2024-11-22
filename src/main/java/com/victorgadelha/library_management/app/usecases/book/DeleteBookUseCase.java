package com.victorgadelha.library_management.app.usecases.book;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.repositories.BookRepository;



@Service
public class DeleteBookUseCase {
    private final BookRepository repository;

    public DeleteBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(UUID id) {
        this.repository.deleteById(id);
    }
}
