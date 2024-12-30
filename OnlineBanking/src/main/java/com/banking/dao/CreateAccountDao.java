package com.banking.dao;

import com.banking.model.AccountType;

import lombok.Data;

@Data
public class CreateAccountDao {
	private Long userId;
	private AccountType accountType;

}
