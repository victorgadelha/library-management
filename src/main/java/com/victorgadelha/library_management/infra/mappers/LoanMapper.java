package com.victorgadelha.library_management.infra.mappers;

import org.mapstruct.Mapper;

import com.victorgadelha.library_management.domain.entities.Loan;
import com.victorgadelha.library_management.web.dtos.loan.LoanDTO;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanDTO toDTO(Loan loan);

    Loan toEntity(LoanDTO loanDTO);
}
