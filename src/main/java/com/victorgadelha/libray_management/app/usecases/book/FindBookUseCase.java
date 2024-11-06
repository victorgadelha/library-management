package com.victorgadelha.libray_management.app.usecases.book;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.domain.repositories.BookRepository;


@Service
public class FindBookUseCase {

    private final BookRepository repository;

    public FindBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Book execute(UUID id) {
        return this.repository.findById(id);
    }
}
