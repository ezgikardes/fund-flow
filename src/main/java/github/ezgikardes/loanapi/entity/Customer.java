package github.ezgikardes.loanapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers", schema = "loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_SURNAME = "surname";
    private static final String COL_CREDIT_LIMIT = "credit_limit";
    private static final String COL_USED_CREDIT_LIMIT = "used_credit_limit";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String COL_ROLE = "role";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID)
    private Long id;

    @Column(name = COL_NAME, nullable = false)
    private String name;

    @Column(name = COL_SURNAME, nullable = false)
    private String surname;

    @Column(name = COL_CREDIT_LIMIT, nullable = false, precision = 19, scale = 2)
    private BigDecimal creditLimit;

    @Column(name = COL_USED_CREDIT_LIMIT, nullable = false, precision = 19, scale = 2)
    private BigDecimal usedCreditLimit;

    @Column(name = COL_USERNAME, nullable = false)
    private String username;

    @Column(name = COL_PASSWORD, nullable = false)
    private String password;

    @Column(name = COL_ROLE, nullable = false)
    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();
}
