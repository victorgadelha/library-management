package com.victorgadelha.libray_management.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.victorgadelha.libray_management.domain.entities.Book;

public interface BookRepository {
    public List<Book> getAll();

    public Book save(Book book);

    public Book update(Book book, UUID id);

    public Optional<Book> getBook(UUID id);

    public void delete(UUID id);

}
