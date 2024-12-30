package com.banking.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.exception.ResourceNotFoundException;
import com.banking.model.Account;
import com.banking.model.AccountType;
import com.banking.repository.AccountRepository;
import com.banking.repository.UserRepository;
import com.banking.model.User;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Account createAccount(Long userId, AccountType accountType) {
		Optional<User> user= userRepository.findById(userId);
		if(user.isPresent()) {
			Account newAccount =new Account();
			newAccount.setUserId(userId);
			newAccount.setBalance(BigDecimal.ZERO);
			newAccount.setAccountNumber(generateAccountNumber());
			newAccount.setAccountType(accountType);
			newAccount.setCreatedAt(LocalDateTime.now());
			return accountRepository.save(newAccount);	
			
		}  
		throw new ResourceNotFoundException("User not found");
	}
	public Account getAccount(Long accountId) {
		return accountRepository.findById(accountId)
				.orElseThrow(()-> new ResourceNotFoundException("Account not found"));
	}

	public Account updateAccount(Long accountId,BigDecimal newBalance) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(()->new ResourceNotFoundException("Account not found"));
		account.setBalance(newBalance);
		account.setUpdatedAt(LocalDateTime.now());
		return accountRepository.save(account);
	}
	public void deleteAccount(Long accountId) {
		Account account=accountRepository.findById(accountId)
				.orElseThrow(()->new ResourceNotFoundException("Account not found"));
		accountRepository.delete(account);
	}
	private String generateAccountNumber() {
		return "AC" + System.currentTimeMillis();
	}
	public List<Account> getAllUserAccounts(Long userId){
		return accountRepository.findByUserId(userId);
		
	}
	public List<Account> getAllAccounts(){
		return accountRepository.findAll();
		}
}
