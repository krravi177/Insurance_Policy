package com.javarnd.project.security.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String Username;
	
	private String password;

	private String role;
	
}
