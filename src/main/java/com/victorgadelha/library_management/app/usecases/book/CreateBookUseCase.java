package com.victorgadelha.library_management.app.usecases.book;

import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.repositories.BookRepository;

@Service
public class CreateBookUseCase {

    private final BookRepository repository;

    public CreateBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Book execute(Book book) {
        return this.repository.save(book);
    }
}
