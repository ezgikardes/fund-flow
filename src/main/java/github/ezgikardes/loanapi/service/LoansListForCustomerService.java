package github.ezgikardes.loanapi.service;

import github.ezgikardes.loanapi.dto.LoanDTO;
import github.ezgikardes.loanapi.dto.LoanListForCustomerRequest;

import java.util.List;

public interface LoansListForCustomerService {
    List<LoanDTO> list(LoanListForCustomerRequest request);
}
