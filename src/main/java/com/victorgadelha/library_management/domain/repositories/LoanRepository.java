package com.victorgadelha.library_management.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.victorgadelha.library_management.domain.entities.Loan;

public interface LoanRepository {
    public List<Loan> findAll();

    public Optional<Loan> findById(UUID id);

    public Loan save(Loan loan);

    public Loan updateById(Loan loan);

    public void deleteById(UUID id);
}
