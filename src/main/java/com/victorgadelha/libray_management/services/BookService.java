package com.victorgadelha.libray_management.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorgadelha.libray_management.DTO.BookDTO;
import com.victorgadelha.libray_management.models.Book;
import com.victorgadelha.libray_management.repositories.BookRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<Book> getAllBooks() {
        return this.repository.findAll();
    }

    @Transactional
    public void saveBook(Book book) {
        this.repository.save(book);
    }

    @Transactional
    public Book updateBook(BookDTO bookDTO, UUID id) {
        var foundBoook = this.repository.findById(id);

        if (!foundBoook.isPresent()) {
            throw new EntityNotFoundException("Livro não encontrado.");
        } else {
            var newBook = foundBoook.get();
            newBook.setTitle(bookDTO.title());
            newBook.setAuthor(bookDTO.author());
            newBook.setIsbn(bookDTO.isbn());
            newBook.setLanguages(bookDTO.languages());

            return this.repository.save(newBook);
        }
    }

    public Book getBookById(UUID id) {
        var foundBoook = this.repository.findById(id);

        if (!foundBoook.isPresent()) {
            throw new EntityNotFoundException("Livro não encontrado.");
        } else {
            var book = foundBoook.get();
            return book;
        }
    }
}