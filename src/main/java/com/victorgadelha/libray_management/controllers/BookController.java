package com.victorgadelha.libray_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.victorgadelha.libray_management.DTO.BookDTO;
import com.victorgadelha.libray_management.models.Book;
import com.victorgadelha.libray_management.repositories.BookRepository;
import com.victorgadelha.libray_management.services.BookService;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/home")
    public String getAllBooks() {
        return "home";
    }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookDTO bookDTO) {
        var book = new Book(bookDTO);
        bookService.saveBook(book);

        var responseDTO = new BookDTO(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(),
                book.getLanguages(),
                book.getCreatedAt());

        return ResponseEntity.ok().body(responseDTO);
    }
}
