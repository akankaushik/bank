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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder 
@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transactionId;
	
	@ManyToOne
	@JoinColumn(name="from_account_id", nullable = true)
	private Account fromAccount;
	
	@ManyToOne
	@JoinColumn(name="to_account_id", nullable = false)
	private Account toAccount;
	
	@Column(nullable=false)
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TransactionType transactionType;
	
	@Column(nullable=false)
	private LocalDateTime transactionDate;
	
	@Column(length=255)
	private String description;

}
