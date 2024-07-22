package com.victorgadelha.libray_management.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorgadelha.libray_management.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

}
