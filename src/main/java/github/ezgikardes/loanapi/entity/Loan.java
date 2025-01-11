package github.ezgikardes.loanapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loans", schema = "loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    private static final String COL_ID = "id";
    private static final String COL_LOAN_AMOUNT = "loan_amount";
    private static final String COL_NUM_OF_INSTALLMENT = "num_of_installment";
    private static final String COL_INTEREST_RATE = "interest_rate";
    private static final String COL_PRINCIPAL_AMOUNT = "principal_amount";
    private static final String COL_INTEREST_AMOUNT = "interest_amount";
    private static final String COL_IS_PAID = "is_paid";
    private static final String COL_CREATE_DATE = "create_date";
    private static final String COL_CUSTOMER_ID = "customer_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long id;

    @Column(name = COL_LOAN_AMOUNT, precision = 19, scale = 2)
    private BigDecimal loanAmount;

    @Column(name = COL_NUM_OF_INSTALLMENT)
    private Integer numOfInstallment;

    @Column(name = COL_INTEREST_RATE)
    private Double interestRate;

    @Column(name = COL_PRINCIPAL_AMOUNT, precision = 19, scale = 2)
    private BigDecimal principalAmount;

    @Column(name = COL_INTEREST_AMOUNT, precision = 19, scale = 2)
    private BigDecimal interestAmount;

    @Column(name = COL_IS_PAID)
    private Boolean isPaid;

    @Column(name = COL_CREATE_DATE)
    private LocalDate createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COL_CUSTOMER_ID)
    private Customer customer;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<LoanInstallment> installments = new ArrayList<>();



}
