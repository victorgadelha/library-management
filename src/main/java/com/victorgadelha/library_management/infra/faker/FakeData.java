package com.victorgadelha.library_management.infra.faker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.infra.repositories.JpaBookRepository;

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
        this.fakeDataGenerator.generateFakeAdmin();
    }
}
