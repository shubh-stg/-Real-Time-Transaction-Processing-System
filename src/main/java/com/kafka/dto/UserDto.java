package com.kafka.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {

	    @NotNull(message = "ID cannot be null")
	    @Digits(integer = 10, fraction = 0)
	    private Long id;

	    @NotBlank(message = "Name cannot be blank")
	    @Size(min = 2, max = 50, message = "Name should be between 2 to 50 characters")
	    private String name;

	    @NotNull(message = "Balance amount cannot be null")
	    @DecimalMin(value = "0.0", inclusive = false)
	    private Float balance;

	    @NotBlank(message = "Username cannot be blank")
	    private String username;

	    @NotBlank(message = "Password cannot be blank")
	    private String password;

	 
	    private String role;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public Float getBalance() {
			return balance;
		}


		public void setBalance(Float balance) {
			this.balance = balance;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getRole() {
			return role;
		}


		public void setRole(String role) {
			this.role = role;
		}


		public UserDto(@NotNull(message = "ID cannot be null") @Digits(integer = 10, fraction = 0) Long id,
				@NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 50, message = "Name should be between 2 to 50 characters") String name,
				@NotNull(message = "Balance amount cannot be null") @DecimalMin(value = "0.0", inclusive = false) Float balance,
				@NotBlank(message = "Username cannot be blank") String username,
				@NotBlank(message = "Password cannot be blank") String password, String role) {
			super();
			this.id = id;
			this.name = name;
			this.balance = balance;
			this.username = username;
			this.password = password;
			this.role = role;
		}


		public UserDto() {
			super();
			// TODO Auto-generated constructor stub
		} 

	
    
}

