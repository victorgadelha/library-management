package com.victorgadelha.library_management.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.victorgadelha.library_management.domain.enums.LoanStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "loan_date")
    @NotNull(message = "A data de emprestimo é obrigatória.")
    LocalDateTime loanDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "return_date")
    LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do empréstimo é obrigatório.")
    private LoanStatus status;

    @ManyToOne
    @NotNull(message = "O livro é obrigatório.")
    @JoinColumn(name = "book_id", nullable = false)
    Book book;

    @ManyToOne
    @NotNull(message = "O usuário é obrigatório.")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    protected void onCreate() {
        loanDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
