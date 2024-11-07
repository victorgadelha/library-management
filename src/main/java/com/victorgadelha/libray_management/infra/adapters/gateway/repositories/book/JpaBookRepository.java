package com.victorgadelha.libray_management.infra.adapters.gateway.repositories.book;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorgadelha.libray_management.domain.entities.Book;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, UUID> {

}
