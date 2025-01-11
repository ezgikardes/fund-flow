package github.ezgikardes.loanapi.repository;

import github.ezgikardes.loanapi.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Loan> {

    Page<Loan> findByCustomer_Id(Long customerId, Pageable pageable);

    @Query("SELECT l FROM Loan l WHERE l.id = :loanId")
    Optional<Loan> findById(Long id);
}
