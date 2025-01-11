package github.ezgikardes.loanapi.repository;

import github.ezgikardes.loanapi.entity.LoanInstallment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanInstallmentRepository extends JpaRepository<LoanInstallment, Long> {

    List<LoanInstallment> findByLoan_IdOrderByDueDateAsc(Long loanId);

    Page<LoanInstallment> findByLoan_IdOrderByDueDateAsc(Long loanId, Pageable pageable);

}
