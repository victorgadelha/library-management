package com.victorgadelha.libray_management.app.usecases.book;


import org.springframework.stereotype.Service;

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.domain.repositories.BookRepository;

@Service
public class CreateBookUseCase {

    private final BookRepository bookRepository;

    public CreateBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book execute(Book book) {
        return this.bookRepository.save(book);
    }
}
