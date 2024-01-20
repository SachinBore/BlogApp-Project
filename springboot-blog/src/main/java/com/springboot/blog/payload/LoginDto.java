package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "LoginDto Model Information")
public class LoginDto {
	
	@Schema(description = "Username or Email of the USER")
	private String usernameOrEmail;
	
	@Schema(description = "Password of the USER")
	private String password;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
