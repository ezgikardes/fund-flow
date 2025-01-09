package github.ezgikardes.fundflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name="user_id")
        private User user;
}
