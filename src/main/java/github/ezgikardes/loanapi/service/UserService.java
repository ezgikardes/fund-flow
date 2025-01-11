package github.ezgikardes.loanapi.service;

import github.ezgikardes.loanapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User registerUser(User user);
    public User getUserById(Long id);
    public Optional<User> getUserByUsername(String username);
    public List<User> getAllUsers();
}
