package com.victorgadelha.libray_management.infra.adapters.gateway.repositories.book;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.infra.persistence.book.BookRepository;
import com.victorgadelha.libray_management.web.dtos.book.BookDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Component
public class JpaBookRepositoryAdapter implements BookRepository {

    private final JpaBookRepository repository;

    public JpaBookRepositoryAdapter(JpaBookRepository repository) {
        this.repository = repository;
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Transactional
    public Book save(Book book) {
        return this.repository.save(book);
    }

    @Transactional
    public Book updateBook(BookDTO bookDTO, UUID id) {
            return this.repository.
        
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

    @Transactional
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