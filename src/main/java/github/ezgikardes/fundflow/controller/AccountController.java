package github.ezgikardes.fundflow.controller;

import github.ezgikardes.fundflow.model.Account;
import github.ezgikardes.fundflow.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/user/{userId}")
    public List<Account> getAccountsByUser(@PathVariable Long userId){
        return accountService.getAccountsByUser(userId);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account createdAccount = accountService.createAccount(account);
        if(createdAccount != null){
            return ResponseEntity.ok(createdAccount);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails){
        Account updatedAccount = accountService.updateAccount(id, accountDetails);
        if(updatedAccount != null){
            return ResponseEntity.ok(updatedAccount);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
