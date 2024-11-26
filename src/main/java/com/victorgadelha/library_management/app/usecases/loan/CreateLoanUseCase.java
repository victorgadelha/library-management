package com.victorgadelha.library_management.app.usecases.loan;

import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.app.usecases.book.FindBookByIdUseCase;
import com.victorgadelha.library_management.app.usecases.user.FindUserByIdUseCase;
import com.victorgadelha.library_management.app.usecases.user.FindUserProfileUseCase;
import com.victorgadelha.library_management.domain.entities.Loan;
import com.victorgadelha.library_management.domain.repositories.LoanRepository;
import com.victorgadelha.library_management.infra.mappers.LoanMapper;
import com.victorgadelha.library_management.infra.mappers.UserMapper;
import com.victorgadelha.library_management.web.dtos.loan.CreateLoanRequestDTO;
import com.victorgadelha.library_management.web.dtos.loan.LoanDTO;

@Service
public class CreateLoanUseCase {

    private final LoanRepository loanRepository;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final FindBookByIdUseCase findBookByIdUseCase;
    private final UserMapper userMapper;
    private final LoanMapper loanMapper;

    public CreateLoanUseCase(
            LoanRepository loanRepository,
            FindUserProfileUseCase findUserProfileUseCase,
            FindUserByIdUseCase findUserByIdUseCase,
            FindBookByIdUseCase findBookByIdUseCase,
            UserMapper userMapper,
            LoanMapper loanMapper) {
        this.userMapper = userMapper;
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.loanRepository = loanRepository;
        this.findBookByIdUseCase = findBookByIdUseCase;
        this.loanMapper = loanMapper;
    }

    public LoanDTO execute(CreateLoanRequestDTO createLoanRequestDTO) {
        var user = this.findUserByIdUseCase.execute(createLoanRequestDTO.userId());
        var book = this.findBookByIdUseCase.execute(createLoanRequestDTO.bookId());
        
        var loan = new Loan();
        loan.setBook(book);
        loan.setUser(userMapper.toEntity(user));
        
        return loanMapper.toDTO(this.loanRepository.save(loan));
    }
}
