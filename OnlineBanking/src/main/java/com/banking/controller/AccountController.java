package com.banking.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dao.CreateAccountDao;
import com.banking.model.Account;
import com.banking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
    private AccountService accountService;



    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountDao createAccountDao) {
        Account newAccount = accountService.createAccount(createAccountDao.getUserId(), createAccountDao.getAccountType());
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccount(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getAllUserAccounts(@PathVariable Long userId) {
        List<Account> accounts = accountService.getAllUserAccounts(userId);
        return ResponseEntity.ok(accounts);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{accountId}/update-balance")
    public ResponseEntity<Account> updateAccountBalance(@PathVariable Long accountId, @RequestParam BigDecimal newBalance) {
        Account updatedAccount = accountService.updateAccount(accountId, newBalance);
        return ResponseEntity.ok(updatedAccount);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
