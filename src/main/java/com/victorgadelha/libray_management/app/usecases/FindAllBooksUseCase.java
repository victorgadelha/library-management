package com.victorgadelha.libray_management.app.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.domain.repositories.BookRepository;

@Service
public class FindAllBooksUseCase {

    private final BookRepository repository;

    public FindAllBooksUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> execute() {
        return this.repository.findAll();
    }
}
