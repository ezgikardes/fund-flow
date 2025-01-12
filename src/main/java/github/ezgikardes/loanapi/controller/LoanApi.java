package github.ezgikardes.loanapi.controller;

import github.ezgikardes.loanapi.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
public interface LoanApi {

    @Operation(summary = "Admin -> Create new Loan")
    @PostMapping("/admin/create-loan")
    ResponseEntity<LoanDTO> createLoan(@RequestBody CreateLoanRequest request);

    @Operation(summary = "Customer/Admin -> List loans for a customer (with pagination)")
    @GetMapping("/customer/list-loans/{customerId}")
    ResponseEntity<List<LoanDTO>> listLoans(
            @PathVariable Long customerId
    );

    @Operation(summary = "Customer/Admin -> List installments for a loan (with pagination)")
    @GetMapping("/customer/list-installments/{loanId}")
    ResponseEntity<List<LoanInstallmentDTO>> listInstallments(
            @PathVariable Long loanId,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize
    );

    @Operation(summary = "Customer/Admin -> Make a loan payment")
    @PostMapping("/customer/pay-loan")
    ResponseEntity<PayInstallmentResponse> payLoan(@RequestBody PayInstallmentRequest request);
}
