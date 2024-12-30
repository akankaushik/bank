package com.banking.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.banking.exception.InsufficientBalanceException;
import com.banking.exception.InvalidTransactionException;
import com.banking.exception.ResourceNotFoundException;
import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;


@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    public Transaction performTransaction(Long fromAccountId, Long toAccountId, BigDecimal amount, String description) {
        Account fromAccount = accountRepository.findById(fromAccountId)
            .orElseThrow(() -> new ResourceNotFoundException("From account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
            .orElseThrow(() -> new ResourceNotFoundException("To account not found"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for transaction");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Transaction amount must be greater than zero");
        }

        // Create and save transaction
        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setDescription(description);

        // Update balances
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return transactionRepository.save(transaction);
    }
    
    public List<Transaction> getTransactionsByAccountId(Long accountId){
    	List<Transaction> fromTransactions= transactionRepository.findByFromAccount_AccountId(accountId);
    	List<Transaction> toTransactions= transactionRepository.findByToAccount_AccountId(accountId);
    	fromTransactions.addAll(toTransactions);
    	return fromTransactions;
    	
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    public List<Transaction> getAllTransactions(){
    	return transactionRepository.findAll();
    }
}
