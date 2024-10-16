package com.javarnd.project.security.response;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String token;
	private String type = "Bearer";
	private String expieryTime;

	public JwtResponse(String accessToken) {

		this.token = accessToken;
	}

	public JwtResponse() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpieryTime() {
		return expieryTime;
	}

	public void setExpieryTime(String expieryTime) {
		this.expieryTime = expieryTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}