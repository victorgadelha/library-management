package com.victorgadelha.library_management.infra.faker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.victorgadelha.library_management.domain.entities.Book;
import com.victorgadelha.library_management.domain.entities.User;
import com.victorgadelha.library_management.domain.enums.Role;
import com.victorgadelha.library_management.domain.repositories.UserRepository;

@Service
public class FakeDataGeneratorImpl implements FakeDataGenerator {
    private final Faker faker = new Faker();
    private final List<Book> books;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public FakeDataGeneratorImpl(List<Book> books, BCryptPasswordEncoder bCryptPasswordEncoder,
            UserRepository userRepository) {

        this.books = new ArrayList<>();
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

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

    @Override
    public User generateFakeAdmin() {

        var user = new User();
        user.setName(faker.name().fullName());
        user.setEmail("admin@email.com");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setRole(Role.ADMIN);

        return this.userRepository.save(user);
    }

}
