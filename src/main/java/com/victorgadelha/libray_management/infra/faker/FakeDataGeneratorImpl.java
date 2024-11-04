package com.victorgadelha.libray_management.infra.faker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.victorgadelha.libray_management.models.Book;

@Service
public class FakeDataGeneratorImpl implements FakeDataGenerator {

private final Faker faker = new Faker();
private final List<Book> books = new ArrayList<>();

    @Override
    public List<Book> generateFakeBooks(int quantity) {
        for (int i = 0; i < quantity; i++) {
            var book = new Book();
            book.setTitle(faker.book().title());
            book.setAuthor(faker.book().author());
            book.setLanguages("Portuguese");
            book.setIsbn(faker.number().digits(13));

            this.books.add(book);
            
        }
        
        return books;
    }
    
}
