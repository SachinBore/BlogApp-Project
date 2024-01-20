package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "RegisterDto Model Information")
public class RegisterDto {

	@Schema(description = "Name of the User who wants to register")
	private String name;
	
	@Schema(description = "username of the User who wants to register")
	private String username;
	
	@Schema(description = "email of the User who wants to register")
	private String email;
	
	@Schema(description = "password for the account")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
