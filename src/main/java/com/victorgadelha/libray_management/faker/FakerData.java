package com.victorgadelha.libray_management.faker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.victorgadelha.libray_management.models.Book;
import com.victorgadelha.libray_management.repositories.BookRepository;

@Service
public class FakerData {
    private final Faker faker;
    private List<Book> books;

    @Autowired
    BookRepository bookRepository;

    public FakerData() {
        this.faker = new Faker();
        this.books = new ArrayList<>();
    }

    public void generateFakeData() {

        for (int i = 0; i < 100; i++) {
            var book = new Book();
            book.setTitle(faker.book().title());
            book.setAuthor(faker.book().author());
            book.setLanguages("Portuguese");
            book.setIsbn(faker.number().digits(13));

            this.books.add(book);
        }
        this.bookRepository.saveAll(books);
    }
}
