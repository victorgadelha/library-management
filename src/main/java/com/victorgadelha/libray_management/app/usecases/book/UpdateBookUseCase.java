package com.victorgadelha.libray_management.app.usecases.book;

import org.springframework.stereotype.Service;

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.domain.repositories.BookRepository;

@Service
public class UpdateBookUseCase {

    private final BookRepository repository;

    public UpdateBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Book execute(Book book) {
        return this.repository.updateById(book);
    }
}
