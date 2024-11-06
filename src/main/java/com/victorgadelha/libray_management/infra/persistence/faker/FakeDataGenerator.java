package com.victorgadelha.libray_management.infra.persistence.faker;

import java.util.List;

import com.victorgadelha.libray_management.domain.entities.Book;

public interface FakeDataGenerator {
    public List<Book> generateFakeBooks(int quantity);
}