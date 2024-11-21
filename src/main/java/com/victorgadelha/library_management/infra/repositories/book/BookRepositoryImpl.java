package com.victorgadelha.library_management.infra.repositories.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.repositories.BookRepository;
import jakarta.transaction.Transactional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    JpaBookRepository repository;

    @Transactional
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
    @Transactional
    public Book updateById(Book book) {
        return this.repository.save(book);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }
}