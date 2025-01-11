package github.ezgikardes.loanapi.service;

import github.ezgikardes.loanapi.model.Account;
import github.ezgikardes.loanapi.model.User;
import github.ezgikardes.loanapi.repository.AccountRepository;
import github.ezgikardes.loanapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Account> getAccountsByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return accountRepository.findByUserId(userId);
    }

    @Override
    public Account createAccount(Account account) {
        User user = userRepository.findById(account.getUser().getId()).orElse(null);
        if(user != null){
          account.setUser(user);
          return accountRepository.save(account);
        }
        return null;
    }

    @Override
    public Account updateAccount(Long id, Account accountDetails) {
        Account account = accountRepository.findById(id).orElse(null);
        if(account != null){
            account.setBalance((accountDetails.getBalance()));
            return accountRepository.save(account);
        }
        return null;
    }
}
