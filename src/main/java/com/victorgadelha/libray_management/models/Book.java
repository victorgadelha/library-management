package com.victorgadelha.libray_management.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.victorgadelha.libray_management.DTO.BookDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "Book")
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 13, max = 13, message = "O ISBN deve ter exatamente 13 caracteres.")
    @NotBlank(message = "O ISBN é obrigatório.")
    @Column(unique = true)
    private String isbn;

    @NotBlank(message = "O título é obrigatório.")
    private String title;

    @NotBlank(message = "O autor é obrigatório.")
    private String author;

    @NotBlank(message = "O idioma é obrigatório.")
    private String languages;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Book(BookDTO bookDTO) {
        this.isbn = bookDTO.isbn();
        this.title = bookDTO.title();
        this.author = bookDTO.author();
        this.languages = bookDTO.languages();
    }

    public Book(Book book) {
        // TODO Auto-generated constructor stub
    }
}
