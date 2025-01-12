package github.ezgikardes.loanapi.utils.mapper;


import github.ezgikardes.loanapi.dto.LoanDTO;
import github.ezgikardes.loanapi.dto.LoanInstallmentDTO;
import github.ezgikardes.loanapi.entity.Loan;
import github.ezgikardes.loanapi.entity.LoanInstallment;

public class LoanMapper {
    public static LoanDTO entityToDTO(Loan loan) {
        LoanDTO dto = new LoanDTO();
        dto.setId(loan.getId());
        dto.setCustomerId(loan.getCustomer().getId());
        dto.setLoanAmount(loan.getLoanAmount());
        dto.setPrincipalAmount(loan.getPrincipalAmount());
        dto.setInterestAmount(loan.getInterestAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setNumberOfInstallment(loan.getNumOfInstallment());
        dto.setCreateDate(loan.getCreateDate());
        dto.setIsPaid(loan.getIsPaid());
        return dto;
    }

    public static LoanInstallmentDTO entityToInstallmentDTO(LoanInstallment inst) {
        LoanInstallmentDTO dto = new LoanInstallmentDTO();
        dto.setId(inst.getId());
        dto.setLoanId(inst.getLoan().getId());
        dto.setAmount(inst.getAmount());
        dto.setInstallmentInterestRate(inst.getInstallmentInterestRate());
        dto.setInterestPortion(inst.getInterestPortion());
        dto.setPrincipalPortion(inst.getPrincipalPortion());
        dto.setPaidAmount(inst.getPaidAmount());
        dto.setDueDate(inst.getDueDate());
        dto.setPaymentDate(inst.getPaymentDate());
        dto.setIsPaid(inst.getIsPaid());
        return dto;
    }
}