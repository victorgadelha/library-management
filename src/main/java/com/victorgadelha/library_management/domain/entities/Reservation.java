package com.victorgadelha.library_management.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.victorgadelha.library_management.domain.enums.ReservationStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "reservation_date")
    @NotNull(message = "A data de reserva é obrigatória.")
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A situação da reserva é obrigatória.")
    private ReservationStatus status;

    @ManyToOne
    @NotNull(message = "O livro é obrigatório.")
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @NotNull(message = "O usuário é obrigatório.")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
