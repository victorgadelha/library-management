package com.victorgadelha.libray_management.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.victorgadelha.libray_management.models.Book;
import com.victorgadelha.libray_management.repositories.BookRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    @Transactional
    public void saveBook(@Valid Book book) {
        this.repository.save(book);
    }

    public List<Book> getAllBooks() {
        return this.repository.findAll();
    }
}