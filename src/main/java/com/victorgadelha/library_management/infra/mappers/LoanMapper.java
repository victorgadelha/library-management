package com.victorgadelha.library_management.infra.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.victorgadelha.library_management.domain.entities.Loan;
import com.victorgadelha.library_management.web.dtos.loan.LoanDTO;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "userId", source = "user.id")
    LoanDTO toDTO(Loan loan);

    Loan toEntity(LoanDTO loanDTO);
}
