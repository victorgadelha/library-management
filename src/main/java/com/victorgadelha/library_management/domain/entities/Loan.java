package com.victorgadelha.library_management.domain.entities;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "created_at")
    @CreationTimestamp
    @NotNull(message = "A data de emprestimo é obrigatória.")
    private LocalDateTime createdAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "expected_return_at")
    private LocalDateTime expectedReturnAt;

    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

    @Column(name = "overdue_at")
    private LocalDateTime overdueAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do empréstimo é obrigatório.")
    private LoanStatus status = LoanStatus.PENDING;

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
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void updateStatus(LoanStatus newStatus) {

        validateStatusTransition(this.status, newStatus);

        switch (newStatus) {
            case APPROVED -> {
                this.approvedAt = LocalDateTime.now();
                this.status = LoanStatus.APPROVED;
            }
            case RETURNED -> {
                this.returnedAt = LocalDateTime.now();
                this.status = LoanStatus.RETURNED;
                this.overdueAt = null;
            }
            case PENDING -> {
                this.approvedAt = null;
                this.returnedAt = null;
                this.overdueAt = null;
                this.status = LoanStatus.PENDING;
            }
            case OVERDUE -> {
                this.overdueAt = LocalDateTime.now();
                this.status = LoanStatus.OVERDUE;
            }
        }
    }

    private void validateStatusTransition(LoanStatus currentStatus, LoanStatus newStatus) {
        switch (currentStatus) {
            case PENDING -> {
                if (!EnumSet.of(LoanStatus.APPROVED, LoanStatus.PENDING).contains(newStatus))
                    throw new IllegalStateException("Transição inválida de PENDING para " + newStatus);
            }
            case APPROVED -> {
                if (!EnumSet.of(LoanStatus.OVERDUE, LoanStatus.RETURNED, LoanStatus.APPROVED).contains(newStatus))
                    throw new IllegalStateException("Transição inválida de APPROVED para " + newStatus);
            }
            case OVERDUE -> {
                if (!EnumSet.of(LoanStatus.RETURNED).contains(newStatus))
                    throw new IllegalStateException("Transição inválida de OVERDUE para " + newStatus);
            }
            case RETURNED -> {
                if (!EnumSet.of(LoanStatus.PENDING).contains(newStatus))
                    throw new IllegalStateException("Transição inválida de RETURNED para " + newStatus);
            }
        }
    }

    public void checkStatusSpecificRules(LoanStatus newStatus) {

        if (newStatus == LoanStatus.APPROVED && this.status == LoanStatus.APPROVED) {
            throw new IllegalStateException("O empréstimo já foi aprovado.");
        }

        if (newStatus == LoanStatus.RETURNED && this.status == LoanStatus.RETURNED) {
            throw new IllegalStateException("O empréstimo já foi devolvido.");
        }
    }

    public void changeStatus(LoanStatus newStatus) {
        checkStatusSpecificRules(newStatus);
        updateStatus(newStatus);
    }
}
