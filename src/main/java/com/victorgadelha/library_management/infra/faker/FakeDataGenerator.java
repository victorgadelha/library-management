package com.victorgadelha.library_management.infra.faker;

import java.util.List;

import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.entities.User;

public interface FakeDataGenerator {
    public List<Book> generateFakeBooks(int quantity);

    public User generateFakeAdmin();
}
