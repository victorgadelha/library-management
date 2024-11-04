package com.victorgadelha.libray_management.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorgadelha.libray_management.domain.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    Page<Book> findAll(Pageable pageable);
}
