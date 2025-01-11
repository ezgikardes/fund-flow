package github.ezgikardes.loanapi.service;

import github.ezgikardes.loanapi.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccountsByUser(Long userId);
    Account createAccount(Account account);
    Account updateAccount(Long id, Account accountDetails);
}
