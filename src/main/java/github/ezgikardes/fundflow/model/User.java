package github.ezgikardes.fundflow.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users", schema = "fundflow")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;
}
