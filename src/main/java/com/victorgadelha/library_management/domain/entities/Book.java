package com.victorgadelha.library_management.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.victorgadelha.library_management.domain.enums.BookStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
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

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do livro é obrigatório.")
    private BookStatus status = BookStatus.AVAILABLE;

    @NotNull(message = "A data de criação é obrigatória.")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "book")
    List<Loan> loans;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
