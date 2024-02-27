package com.hstore.vn.payload.response;

public class AuthResponse {
	
	public String accessToken;
	
	public String type = "Bearer ";

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AuthResponse(String accessToken) {
		this.accessToken = accessToken;
	}

}
