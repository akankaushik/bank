package com.banking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long accountId;
	
	@Column(nullable=false)
	private Long userId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private AccountType accountType;
	
	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal balance;
	
	@Column(nullable=false)
	private String accountNumber;
	
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@Column(nullable=false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt =LocalDateTime.now();
		
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt =LocalDateTime.now();
		
	}
	
	
	
	

}
