package com.victorgadelha.libray_management.app.usecases;

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.infra.persistence.book.BookRepository;
import com.victorgadelha.libray_management.web.dtos.book.BookDTO;

public class CreateBookUseCase {

    private final BookRepository bookRepository;

    public CreateBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO execute(BookDTO bookDTO) {
        var book = new Book();
        book.setTitle(bookDTO.title());
        book.setIsbn(bookDTO.isbn());
        book.setAuthor(bookDTO.author());
        book.setLanguages(bookDTO.languages());

     this.bookRepository.save(book);

     return bookDTO;
    }
}
