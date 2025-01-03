package com.banking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dao.UserRegisterDao;
import com.banking.model.AuthenticationRequest;
import com.banking.model.AuthenticationResponse;
import com.banking.model.User;
import com.banking.service.UserService;

@RestController
@RequestMapping
public class UserController {
	
	private final UserService userService;


			public UserController (UserService userService) {

			this.userService = userService;
			
			}

			@PostMapping("/auth/register") 
			public ResponseEntity<User> registerUser (@RequestBody UserRegisterDao userRegisterDao) {


			User registeredUser = userService.register(userRegisterDao);

			return ResponseEntity.ok(registeredUser);
			}

			@PostMapping("/auth/login")
			public ResponseEntity<AuthenticationResponse> authenticateUser(

			@RequestBody AuthenticationRequest authenticationRequest) {

			AuthenticationResponse response = userService.verify(authenticationRequest);

			return ResponseEntity.ok(response);
			}

			@PreAuthorize("hasRole('ADMIN')")
			@GetMapping("/users/all")
			public ResponseEntity<List<User>> getAllUsers() {

			List<User> users =userService.getAllUsers();

			return ResponseEntity.ok(users);
			}
			
            @PreAuthorize("hasRole('ADMIN')")
			@DeleteMapping("/(userId)")
			public ResponseEntity <Void> deleteUser(@PathVariable Long userId) {

			userService.deleteUser(userId);

			return ResponseEntity.noContent().build();
			}

}
