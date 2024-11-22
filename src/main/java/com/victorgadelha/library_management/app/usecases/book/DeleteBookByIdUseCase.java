package com.victorgadelha.library_management.app.usecases.book;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorgadelha.library_management.domain.repositories.BookRepository;

@Service
public class DeleteBookByIdUseCase {

    private final BookRepository repository;
    private final FindBookByIdUseCase findBookByIdUseCase;

    public DeleteBookByIdUseCase(BookRepository repository, FindBookByIdUseCase findBookByIdUseCase) {
        this.repository = repository;
        this.findBookByIdUseCase = findBookByIdUseCase;
    }

    @Transactional
    public void execute(UUID id) {
        this.findBookByIdUseCase.execute(id);
        this.repository.deleteById(id);
    }

}
