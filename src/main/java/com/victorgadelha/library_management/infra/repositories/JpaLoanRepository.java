package com.victorgadelha.library_management.infra.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorgadelha.library_management.domain.entities.Loan;

@Repository
public interface JpaLoanRepository extends JpaRepository<Loan, UUID> {
    
}
