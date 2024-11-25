package com.victorgadelha.library_management.web.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgadelha.library_management.app.usecases.book.CreateBookUseCase;
import com.victorgadelha.library_management.app.usecases.book.DeleteBookByIdUseCase;
import com.victorgadelha.library_management.app.usecases.book.FindAllBooksUseCase;
import com.victorgadelha.library_management.app.usecases.book.FindBookByIdUseCase;
import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.web.dtos.book.BookDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class BookController {

        private final CreateBookUseCase createBookUseCase;
        private final FindAllBooksUseCase findAllBooksUseCase;
        private final FindBookByIdUseCase findBookUseCase;
        private final DeleteBookByIdUseCase deleteBookUseCase;

        public BookController(
                        CreateBookUseCase createBookUseCase,
                        FindAllBooksUseCase findAllBooksUseCase,
                        FindBookByIdUseCase findBookUseCase,
                        DeleteBookByIdUseCase deleteBookUseCase) {
                this.createBookUseCase = createBookUseCase;
                this.findAllBooksUseCase = findAllBooksUseCase;
                this.findBookUseCase = findBookUseCase;
                this.deleteBookUseCase = deleteBookUseCase;
        }

        @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_BASIC')")
        @GetMapping("/books")
        public ResponseEntity<List<BookDTO>> findAllBooks() {
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

        @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_BASIC')")
        @GetMapping("/books/{id}")
        public ResponseEntity<BookDTO> findBookByID(@PathVariable UUID id) {
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

        @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
        @PostMapping("/books")
        public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookDTO body) {
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

        @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
        @PutMapping("/books/{id}")
        public ResponseEntity<BookDTO> updateBookById(
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

        @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
        @DeleteMapping("/books/{id}")
        public ResponseEntity<Void> deleteBookById(@PathVariable UUID id) {
                this.deleteBookUseCase.execute(id);

                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

}
