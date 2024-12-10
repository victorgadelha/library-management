package com.victorgadelha.library_management.domain.entities;

import java.time.LocalDateTime;
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
	private LoanStatus status = LoanStatus.LOANED;

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

}
