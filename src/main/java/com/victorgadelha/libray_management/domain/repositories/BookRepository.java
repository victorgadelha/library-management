package com.victorgadelha.libray_management.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.victorgadelha.libray_management.domain.entities.Book;

public interface BookRepository {

    public List<Book> findAll();

    public Optional<Book> findById(UUID id);

    public Book save(Book book);

    public Book updateById(Book book);

    public void deleteById(UUID id);

}
