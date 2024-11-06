package com.victorgadelha.libray_management.app.usecases.book;

import java.util.List;

import org.springframework.stereotype.Service;

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.domain.repositories.BookRepository;

@Service
public class FindAllBooksUseCase {
    private final BookRepository bookRepository;

    public FindAllBooksUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> execute() {
        return this.bookRepository.findAll();
    }
}
