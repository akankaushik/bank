package com.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	
	List<Transaction> findByFromAccount_AccountId(Long accountId);
	
	List<Transaction> findByToAccount_AccountId(Long accountId);

	

}
