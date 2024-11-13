package com.victorgadelha.library_management.web.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import com.victorgadelha.library_management.app.usecases.book.CreateBookUseCase;
import com.victorgadelha.library_management.app.usecases.book.DeleteBookUseCase;
import com.victorgadelha.library_management.app.usecases.book.FindAllBooksUseCase;
import com.victorgadelha.library_management.app.usecases.book.FindBookUseCase;
import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.web.dtos.BookDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class BookController {

        @Autowired
        CreateBookUseCase createBookUseCase;

        @Autowired
        FindAllBooksUseCase findAllBooksUseCase;

        @Autowired
        FindBookUseCase findBookUseCase;

        @Autowired
        DeleteBookUseCase deleteBookUseCase;

        @GetMapping("/books")
        public ResponseEntity<List<BookDTO>> findAll() {
                var books = this.findAllBooksUseCase.execute();

                return ResponseEntity.status(HttpStatus.OK)
                                .body(books.stream()
                                                .map(book -> new BookDTO(
                                                                book.getId(),
                                                                book.getIsbn(),
                                                                book.getTitle(),
                                                                book.getAuthor(),
                                                                book.getLanguages(),
                                                                book.getCreatedAt(),
                                                                book.getUpdatedAt()))
                                                .collect(Collectors.toList()));
        }

        @GetMapping("/books/{id}")
        public ResponseEntity<BookDTO> getBookById(@PathVariable UUID id) {
                var book = this.findBookUseCase.execute(id);
                var bookDTO = new BookDTO(
                                id,
                                book.getIsbn(),
                                book.getTitle(),
                                book.getAuthor(),
                                book.getLanguages(),
                                book.getCreatedAt(),
                                book.getUpdatedAt());

                return ResponseEntity.status(HttpStatus.OK).body(bookDTO);
        }

        @PostMapping("/books")
        public ResponseEntity<BookDTO> save(@Valid @RequestBody BookDTO body) {
                var book = new Book();
                book.setIsbn(body.isbn());
                book.setTitle(body.title());
                book.setAuthor(body.author());
                book.setLanguages(body.languages());

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

        @PutMapping("/books/{id}")
        public ResponseEntity<BookDTO> updateBook(
                        @PathVariable UUID id,
                        @Valid @RequestBody BookDTO body) {

                Book bookToUpdate = this.findBookUseCase.execute(id);
                bookToUpdate.setTitle(body.title());
                bookToUpdate.setAuthor(body.author());
                bookToUpdate.setLanguages(body.languages());
                bookToUpdate.setUpdatedAt(LocalDateTime.now());

                var bookDTO = new BookDTO(
                                bookToUpdate.getId(),
                                bookToUpdate.getIsbn(),
                                bookToUpdate.getTitle(),
                                bookToUpdate.getAuthor(),
                                bookToUpdate.getLanguages(),
                                bookToUpdate.getCreatedAt(),
                                bookToUpdate.getUpdatedAt());

                return ResponseEntity.status(HttpStatus.OK).body(bookDTO);
        }

        @DeleteMapping("/books/{id}")
        public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
                this.deleteBookUseCase.execute(id);

                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

}
