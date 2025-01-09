package github.ezgikardes.fundflow.repository;

import github.ezgikardes.fundflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
