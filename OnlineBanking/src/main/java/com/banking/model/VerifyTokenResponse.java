package com.banking.model;

import lombok.Data;

@Data
public class VerifyTokenResponse {
	private Long userId;
	private String token;

}
