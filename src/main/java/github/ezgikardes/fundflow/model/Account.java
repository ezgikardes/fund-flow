package github.ezgikardes.fundflow.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts", schema = "fundflow")
public class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="account_number")
        private String accountNumber;

        @Column(name="balance")
        private Double balance;

        @ManyToOne
        @JoinColumn(name="user_id")
        private User user;
}
