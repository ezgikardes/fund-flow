package github.ezgikardes.loanapi.service;

import github.ezgikardes.loanapi.dto.CreateLoanRequest;
import github.ezgikardes.loanapi.dto.LoanDTO;

public interface LoanCreationService {
    LoanDTO create(CreateLoanRequest request);
}
