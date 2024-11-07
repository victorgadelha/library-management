package com.victorgadelha.libray_management.web.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.libray_management.app.usecases.CreateBookUseCase;
import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.infra.adapters.gateway.repositories.book.JpaBookRepository;
import com.victorgadelha.libray_management.infra.adapters.gateway.repositories.book.BookRepositoryImpl;
import com.victorgadelha.libray_management.web.dtos.BookDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class BookController {

    @Autowired
    JpaBookRepository bookRepository;

    @Autowired
    BookRepositoryImpl bookService;

    @Autowired
    CreateBookUseCase createBookUseCase;

    // @GetMapping("/books")
    // public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue =
    // "0") int page,
    // @RequestParam(defaultValue = "10") int size) {
    // Pageable pageable = PageRequest.of(page, size);
    // var books = this.bookService.getAllBooks(pageable);

    // return ResponseEntity.status(HttpStatus.OK).body(books);
    // }

    // @GetMapping("/books/{id}")
    // public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
    // var book = this.bookService.getBookById(id);

    // return ResponseEntity.status(HttpStatus.OK).body(book);
    // }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> save(@Valid @RequestBody BookDTO body) {

        var book = new Book();
        book.setIsbn(body.isbn());
        book.setTitle(body.title());
        book.setAuthor(body.author());
        book.setLanguages(body.languages());
        book.setCreatedAt(body.createdAt());

        this.createBookUseCase.execute(book);

        var bookDTO = new BookDTO(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getLanguages(),
                book.getCreatedAt(),
                book.getUpdatedAt());

        return ResponseEntity.status(HttpStatus.CREATED).body(bookDTO);
    }

    // @PutMapping("/books/{id}")
    // public ResponseEntity<EditBookResponseDTO> updateBook(@PathVariable UUID id,
    // @Valid @RequestBody BookDTO bookDTO) {

    // Book updatedBook = this.bookService.updateBook(bookDTO, id);

    // var responseDTO = new EditBookResponseDTO(updatedBook.getId(),
    // updatedBook.getIsbn(), updatedBook.getTitle(),
    // updatedBook.getAuthor(),
    // updatedBook.getLanguages(), updatedBook.getUpdatedAt());

    // return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    // }

    // @DeleteMapping("/books/{id}")
    // public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
    // this.bookService.deleteBookByID(id);

    // return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    // }

}
