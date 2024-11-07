package com.victorgadelha.libray_management.infra.adapters.gateway.repositories.book;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.domain.repositories.BookRepository;
import jakarta.transaction.Transactional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    JpaBookRepository repository;

    // public Book<Book> getAll(Pageable pageable) {
    // return this.repository.findAll(pageable);
    // }

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
    public Book update(Book book, UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    // @Transactional
    // public Book update(Book book, UUID id) {
    // var foundBoook = this.repository.findById(id);

    // if (!foundBoook.isPresent()) {
    // throw new EntityNotFoundException("Livro não encontrado.");
    // } else {
    // var newBook = foundBoook.get();
    // newBook.setTitle(book.title());
    // newBook.setAuthor(book.author());
    // newBook.setIsbn(book.isbn());
    // newBook.setLanguages(book.languages());
    // newBook.setUpdatedAt(LocalDateTime.now());

    // return this.repository.save(newBook);
    // }
    // }

    // public Optional<Book> getBook(UUID id) {
    // var foundBoook = this.repository.findById(id);

    // if (!foundBoook.isPresent()) {
    // throw new EntityNotFoundException("Livro não encontrado.");
    // } else {
    // var book = foundBoook.get();
    // return book;
    // }
    // }

    // public void delete(UUID id) {
    // var foundBoook = this.repository.findById(id);

    // if (!foundBoook.isPresent()) {
    // throw new EntityNotFoundException("Livro não encontrado.");
    // } else {
    // this.repository.deleteById(id);
    // return;
    // }
    // }
}