package com.victorgadelha.library_management.infra.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.victorgadelha.library_management.domain.entities.Loan;
import com.victorgadelha.library_management.domain.repositories.LoanRepository;
import com.victorgadelha.library_management.infra.repositories.JpaLoanRepository;

@Component
public class LoanRepositoryImpl implements LoanRepository {

    private final JpaLoanRepository jpaLoanRepository;

    public LoanRepositoryImpl(JpaLoanRepository jpaLoanRepository) {
        this.jpaLoanRepository = jpaLoanRepository;
    }

    @Override
    public List<Loan> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<Loan> findById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Loan save(Loan loan) {
        return this.jpaLoanRepository.save(loan);
    }

    @Override
    public Loan updateById(Loan loan) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    @Override
    public void deleteById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
