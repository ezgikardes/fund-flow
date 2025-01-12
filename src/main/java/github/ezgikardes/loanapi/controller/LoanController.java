package github.ezgikardes.loanapi.controller;

import github.ezgikardes.loanapi.dto.*;
import github.ezgikardes.loanapi.service.LoanCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LoanController implements LoanApi{

    private LoanCreationService loanCreationService;


    @Override
    public ResponseEntity<LoanDTO> createLoan(CreateLoanRequest request) {

        LoanDTO loanDTO = loanCreationService.create(request);
        return new ResponseEntity<>(loanDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<LoanDTO>> listLoans(Long customerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<LoanInstallmentDTO>> listInstallments(Long loanId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<PayInstallmentResponse> payLoan(PayInstallmentRequest request) {
        return null;
    }
}
