package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWTAuthResponse Information")
public class JWTAuthResponse {
	
	@Schema(description = "Access Token of the USER")
	private String accessToken;
	
	@Schema(description = "Access Token type, it is always Bearer")
	private String tokenType = "Bearer";

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}
