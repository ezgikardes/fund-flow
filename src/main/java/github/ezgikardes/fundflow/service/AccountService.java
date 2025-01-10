package github.ezgikardes.fundflow.service;

import github.ezgikardes.fundflow.model.Account;

import java.util.List;

public interface AccountService {
    public List<Account> getAccountsByUser(Long userId);

    public Account createAccount(Account account);
    Account updateAccount(Long id, Account accountDetails);
}
