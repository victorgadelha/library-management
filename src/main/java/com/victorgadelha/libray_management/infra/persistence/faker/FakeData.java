package com.victorgadelha.libray_management.infra.persistence.faker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.victorgadelha.libray_management.infra.adapters.gateway.repositories.book.JpaBookRepository;

@Service
public class FakeData implements CommandLineRunner {

    private final JpaBookRepository bookRepository;
    private final FakeDataGenerator fakeDataGenerator;
    
    public FakeData(JpaBookRepository bookRepository, FakeDataGeneratorImpl fakeDataGenerator) {
        this.bookRepository = bookRepository;
        this.fakeDataGenerator = fakeDataGenerator;
    }

    @Override
    public void run(String... args) throws Exception {
        var books = this.fakeDataGenerator.generateFakeBooks(100);
        this.bookRepository.saveAll(books);
    }
}
