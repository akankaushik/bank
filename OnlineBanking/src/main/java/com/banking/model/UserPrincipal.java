package com.banking.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	
	private Long userId;
	private String name;
	private String email;
	private String password;
	private String role;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipal(
			Long userId,
			String name,
			String email,
			String password,
			String role,
			Collection<? extends GrantedAuthority> authorities
			
			) {
		this.userId=userId;
		this.name=name;
		this.email=email;
		this.password=password;
		this.role=role;
		this.authorities=authorities;
		
	}
	
	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_"+ user.getRole().name()));
		
		return new UserPrincipal(
				user.getUserId(),
				user.getName(),
				user.getEmail(),
				user.getPassword(),
				user.getRole().name(),
				authorities);
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRole() {
		return role;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
		
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
		
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
		
	}
	@Override
	public boolean isEnabled() {
		return true;
		
	}

}
 