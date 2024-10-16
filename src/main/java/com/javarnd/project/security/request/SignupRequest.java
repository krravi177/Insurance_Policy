package com.javarnd.project.security.request;


import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author TA Admin
 *
 * 
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest  implements Serializable {
	
 private static final long serialVersionUID = 1L;

  private String username;

  private String email;

  private Set<String> role;

  private String password;

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

public Set<String> getRole() {
	return role;
}

public void setRole(Set<String> role) {
	this.role = role;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}
  


}
