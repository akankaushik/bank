package com.banking.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
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
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=20)
	private UserRole role;
	
	@Column(nullable =false, unique=true,length=100)
	private String name;
	
	@Column(nullable =false, unique=true,length=100)
	private String email;
	
	@Column(nullable =false,length=100)
	private String password;
	
	@Column(nullable =false, unique=true,length=100)
	private String address;
	
	@Column(unique=true,length=15)
    private String phoneNumber;
	
	@Column(nullable=false, updatable=false)
	@Builder.Default
	private LocalDateTime createdAt = LocalDateTime.now();
	
	
	@Column(nullable=false)
	@Builder.Default
	private LocalDateTime updatedAt = LocalDateTime.now();
	
	@PreUpdate
	public void preUpdate() {
		this.updatedAt=LocalDateTime.now();
	}
	
	
	
	

}
