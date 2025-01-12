package github.ezgikardes.loanapi.service;

import github.ezgikardes.loanapi.dto.CreateLoanRequest;
import github.ezgikardes.loanapi.dto.LoanDTO;
import github.ezgikardes.loanapi.entity.Customer;
import github.ezgikardes.loanapi.entity.Loan;
import github.ezgikardes.loanapi.entity.LoanInstallment;
import github.ezgikardes.loanapi.exceptions.ApiException;
import github.ezgikardes.loanapi.repository.CustomerRepository;
import github.ezgikardes.loanapi.repository.LoanInstallmentRepository;
import github.ezgikardes.loanapi.repository.LoanRepository;
import github.ezgikardes.loanapi.utils.constants.LoanServiceConstants;
import github.ezgikardes.loanapi.utils.mapper.LoanMapper;
import github.ezgikardes.loanapi.validations.LoanValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;

@RequiredArgsConstructor
@Service
public class LoanCreationServiceImpl implements LoanCreationService {

    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;
    private final LoanInstallmentRepository loanInstallmentRepository;


    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public LoanDTO create(CreateLoanRequest request) {
        // müşteri limiti yeterli olacak.
        // kredi başvurusu sadece admin tarafından yapılabilir.
        //interest rate 0.1 - 0.5
        //6,9,12,24 => amortizaiton.
        // ödeme planı her ayın ilk günü.

        // validate payload.
        LoanValidator.validateCreateLoanRequest(request);

        // müşteri getiriyi getir.
        Customer customer = fetchCustomer(request.getCustomerId());
        // limit varmı
        BigDecimal newUsedCredit = checkAndCalculateNewUsedCredit(customer, request.getPrincipalAmount());
        BigDecimal totalLoanAmount = calculateTotalLoanAmount(request);

        Loan loan = createLoanEntity(customer, totalLoanAmount, request);
        Loan savedLoan = loanRepository.save(loan);
        updateCustomerCredit(customer, newUsedCredit);
        createLoanInstallments(savedLoan);


        return LoanMapper.entityToDTO(savedLoan);
    }

    private void createLoanInstallments(Loan loan) {

        double monthlyInterestRate = loan.getInterestRate() / 12;

        double P = loan.getPrincipalAmount().doubleValue();
        int n = loan.getNumOfInstallment();
        double i = monthlyInterestRate; // 0.01 gibi

        double monthlyPaymentDouble;
        if (i == 0.0) {

            monthlyPaymentDouble = P / n;
        } else {
            monthlyPaymentDouble = P * (i * Math.pow(1 + i, n)) / (Math.pow(1 + i, n) - 1);
        }

        BigDecimal monthlyPayment = BigDecimal.valueOf(monthlyPaymentDouble).setScale(2, RoundingMode.HALF_UP);

        BigDecimal currentBalance = loan.getPrincipalAmount();

        LocalDate firstInstallmentDueDate = LocalDate.now(ZoneId.of("UTC"))
                .plusMonths(1)
                .withDayOfMonth(1);

        for (int installmentIndex = 0; installmentIndex < n; installmentIndex++) {
            LoanInstallment installment = new LoanInstallment();
            installment.setLoan(loan);
            installment.setIsPaid(false);
            installment.setPaidAmount(BigDecimal.ZERO);
            installment.setPaymentDate(null);

            installment.setDueDate(firstInstallmentDueDate.plusMonths(installmentIndex));

            BigDecimal interestForThisInstallment = currentBalance
                    .multiply(BigDecimal.valueOf(monthlyInterestRate))
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal principalForThisInstallment = monthlyPayment.subtract(interestForThisInstallment);


            if (principalForThisInstallment.compareTo(currentBalance) > 0) {
                principalForThisInstallment = currentBalance;
            }

            installment.setAmount(monthlyPayment);

            installment.setPrincipalPortion(principalForThisInstallment);
            installment.setInterestPortion(interestForThisInstallment);

            installment.setInstallmentInterestRate(BigDecimal.valueOf(monthlyInterestRate).setScale(4, RoundingMode.HALF_UP));


            loanInstallmentRepository.save(installment);

            currentBalance = currentBalance.subtract(principalForThisInstallment);
            if (currentBalance.compareTo(BigDecimal.ZERO) < 0) {
                currentBalance = BigDecimal.ZERO;
            }
        }
    }

    private void updateCustomerCredit(Customer customer, BigDecimal newUsedCredit) {
        customer.setUsedCreditLimit(newUsedCredit);
        customerRepository.save(customer);
    }

    private Loan createLoanEntity(Customer customer, BigDecimal totalLoanAmount, CreateLoanRequest request) {
        Loan loan = new Loan();
        loan.setCustomer(customer);
        loan.setLoanAmount(totalLoanAmount);
        loan.setNumOfInstallment(request.getNumberOfInstallment());
        loan.setInterestAmount(totalLoanAmount.subtract(request.getPrincipalAmount()));
        loan.setInterestRate(request.getInterestRate());
        loan.setPrincipalAmount(request.getPrincipalAmount());
        loan.setCreateDate(LocalDate.now(ZoneId.of("UTC")));
        loan.setIsPaid(false);
        return loan;
    }

    private BigDecimal calculateTotalLoanAmount(CreateLoanRequest request) {
        BigDecimal interestFactor = BigDecimal.valueOf(1).add(BigDecimal.valueOf(request.getInterestRate()));
        return request.getPrincipalAmount().multiply(interestFactor);
    }

    private BigDecimal checkAndCalculateNewUsedCredit(Customer customer, BigDecimal principalAmount) {
        BigDecimal newUsed = customer.getUsedCreditLimit().add(principalAmount);
        LoanValidator.checkLoanLimitExceed(newUsed, customer);
        return newUsed;
    }

    private Customer fetchCustomer(Long customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ApiException(LoanServiceConstants.ERROR_CUSTOMER_NOT_FOUND + customerId, HttpStatus.NOT_FOUND));
    }
}

