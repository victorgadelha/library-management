package com.victorgadelha.libray_management.infra.faker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.victorgadelha.libray_management.repositories.BookRepository;

@Service
public class FakeData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final FakeDataGenerator fakeDataGenerator;
    
    public FakeData(BookRepository bookRepository, FakeDataGeneratorImpl fakeDataGenerator) {
        this.bookRepository = bookRepository;
        this.fakeDataGenerator = fakeDataGenerator;
    }

    @Override
    public void run(String... args) throws Exception {
        var books = this.fakeDataGenerator.generateFakeBooks(100);
        this.bookRepository.saveAll(books);
    }
}
