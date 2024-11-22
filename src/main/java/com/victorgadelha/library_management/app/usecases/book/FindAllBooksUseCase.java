package com.victorgadelha.library_management.app.usecases.book;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.repositories.BookRepository;



@Service
public class FindAllBooksUseCase {

    private final BookRepository repository;

    public FindAllBooksUseCase(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Book> execute() {
        return this.repository.findAll();
    }
}
