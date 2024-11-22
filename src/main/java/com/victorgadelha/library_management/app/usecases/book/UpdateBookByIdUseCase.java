package com.victorgadelha.library_management.app.usecases.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.repositories.BookRepository;



@Service
public class UpdateBookByIdUseCase {

    private final BookRepository repository;

    public UpdateBookByIdUseCase(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Book execute(Book book) {
        return this.repository.updateById(book);
    }
}
