package github.ezgikardes.fundflow.service;

import github.ezgikardes.fundflow.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccountsByUser(Long userId);
    Account createAccount(Account account);
    Account updateAccount(Long id, Account accountDetails);
}
