package com.victorgadelha.libray_management.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Book> getAllBooks(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Transactional
    public Book saveBook(BookDTO bookDTO) {
        var book = new Book();
        book.setTitle(bookDTO.title());
        book.setAuthor(bookDTO.author());
        book.setIsbn(bookDTO.isbn());
        book.setLanguages(bookDTO.languages());

        return this.repository.save(book);
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
            newBook.setUpdatedAt(LocalDateTime.now());

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

    public void deleteBookByID(UUID id) {
        var foundBoook = this.repository.findById(id);

        if (!foundBoook.isPresent()) {
            throw new EntityNotFoundException("Livro não encontrado.");
        } else {
            this.repository.deleteById(id);
            return;
        }
    }
}