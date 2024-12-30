package com.banking.dao;

import lombok.Data;

@Data
public class UserRegisterDao {
	
	private String name;
	private String email;
	private String password;
	private String address;
	private String phoneNumber;

}
