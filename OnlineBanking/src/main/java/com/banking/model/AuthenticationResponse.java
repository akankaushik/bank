package com.banking.model;

import lombok.Data;

@Data
public class AuthenticationResponse {
	private User user;
	private String token;

}
