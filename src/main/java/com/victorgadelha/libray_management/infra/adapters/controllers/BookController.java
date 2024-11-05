package com.victorgadelha.libray_management.infra.adapters.controllers;

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

import com.victorgadelha.libray_management.domain.entities.Book;
import com.victorgadelha.libray_management.infra.adapters.gateway.repositories.book.JpaBookRepository;
import com.victorgadelha.libray_management.infra.adapters.gateway.repositories.book.JpaBookRepositoryAdapter;
import com.victorgadelha.libray_management.web.dtos.book.BookDTO;
import com.victorgadelha.libray_management.web.dtos.book.EditBookResponseDTO;
import com.victorgadelha.libray_management.web.dtos.book.BookResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class BookController {

    @Autowired
    JpaBookRepository bookRepository;

    @Autowired
    JpaBookRepositoryAdapter bookService;

    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        var books = this.bookService.getAllBooks(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        var book = this.bookService.getBookById(id);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDTO> saveBook(@Valid @RequestBody BookDTO bookDTO) {
        var book = this.bookService.save(bookDTO);

        var responseDTO = new BookResponseDTO(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(),
                book.getLanguages(),
                book.getCreatedAt(), book.getUpdatedAt());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<EditBookResponseDTO> updateBook(@PathVariable UUID id, @Valid @RequestBody BookDTO bookDTO) {

        Book updatedBook = this.bookService.updateBook(bookDTO, id);

        var responseDTO = new EditBookResponseDTO(updatedBook.getId(), updatedBook.getIsbn(), updatedBook.getTitle(),
                updatedBook.getAuthor(),
                updatedBook.getLanguages(), updatedBook.getUpdatedAt());

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        this.bookService.deleteBookByID(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
