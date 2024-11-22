package com.victorgadelha.library_management.infra.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.repositories.BookRepository;
import com.victorgadelha.library_management.infra.repositories.JpaBookRepository;


@Repository
public class BookRepositoryImpl implements BookRepository {

    private final JpaBookRepository repository;

    public BookRepositoryImpl(JpaBookRepository repository) {
        this.repository = repository;
    }

    public Book save(Book book) {
        return this.repository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Book> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public Book updateById(Book book) {
        return this.repository.save(book);
    }

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }
}