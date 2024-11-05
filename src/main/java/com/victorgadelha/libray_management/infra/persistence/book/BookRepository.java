package com.victorgadelha.libray_management.infra.persistence.book;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.web.dtos.book.BookDTO;


public interface BookRepository {
    public Page<Book> getAllBooks(Pageable pageable);
    public Book saveBook(BookDTO bookDTO);
    public Book updateBook(BookDTO bookDTO, UUID id);
    public Book getBookById(UUID id);
    public void deleteBookByID(UUID id);

}
