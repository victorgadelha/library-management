package com.victorgadelha.libray_management.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.libray_management.DTO.BookDTO;
import com.victorgadelha.libray_management.models.Book;
import com.victorgadelha.libray_management.repositories.BookRepository;
import com.victorgadelha.libray_management.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        var books = this.bookService.getAllBooks();

        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        var book = this.bookService.getBookById(id);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookDTO bookDTO) {
        var book = new Book(bookDTO);
        this.bookService.saveBook(book);

        var responseDTO = new BookDTO(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(),
                book.getLanguages(),
                book.getCreatedAt());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable UUID id, @Valid @RequestBody BookDTO bookDTO) {

        Book updatedBook = this.bookService.updateBook(bookDTO, id);

        var responseDTO = new BookDTO(updatedBook.getId(), updatedBook.getIsbn(), updatedBook.getTitle(),
                updatedBook.getAuthor(),
                updatedBook.getLanguages(), updatedBook.getCreatedAt());

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

}
