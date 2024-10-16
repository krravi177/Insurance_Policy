package com.javarnd.project.security.controller;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javarnd.project.security.dao.IRoleRepository;
import com.javarnd.project.security.dao.UserRepository;
import com.javarnd.project.security.entity.ERole;
import com.javarnd.project.security.entity.RoleEntity;
import com.javarnd.project.security.entity.UserEntity;
import com.javarnd.project.security.request.LoginRequest;
import com.javarnd.project.security.request.SignupRequest;
import com.javarnd.project.security.response.JwtResponse;
import com.javarnd.project.security.service.UserDetailsImpl;
import com.javarnd.project.security.util.JwtTokenUtill;


/**
 * @author TA Admin
 *
 * 
 */

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	IRoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtTokenUtill jwtTokenUtill;


	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			JwtResponse jwt = jwtTokenUtill.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toSet());

			// Verify if the user has the required role
			if (!roles.contains(loginRequest.getRole())) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have access");
			}

			return ResponseEntity.ok(jwt);

		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid username or password");
		}
	}

	@PostMapping("/authenticate/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body("Error: Email is already in use!");
		}

		// Create new user's account
		UserEntity user = new UserEntity(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<RoleEntity> roles = new HashSet<>();

		System.out.println(strRoles);

		if (strRoles == null) {
			RoleEntity userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "customer":
					RoleEntity empRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(empRole);
					break;
				case "agent":
					RoleEntity modRole = roleRepository.findByName(ERole.ROLE_AGENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
				}
			});
		}

		user.setRoles(roles);
		UserEntity save = userRepository.save(user);
		if (save != null) {
			return ResponseEntity.ok("User Created");
		}
		return ResponseEntity.ok("User not Created");

	}

	@PostConstruct
	public ResponseEntity<String> initializeDefaultRoles() {
		if (roleRepository.findAll().isEmpty()) {
			try {
				List<RoleEntity> roleList = Arrays.asList(new RoleEntity(ERole.ROLE_ADMIN),
						new RoleEntity(ERole.ROLE_AGENT), new RoleEntity(ERole.ROLE_CUSTOMER));

				List<RoleEntity> savedRoles = roleRepository.saveAll(roleList);

				if (!savedRoles.isEmpty()) {
					return ResponseEntity.ok("Default roles have been initialized successfully.");
				} else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Failed to initialize default roles.");
				}
			} catch (DataAccessException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("An error occurred while initializing default roles: " + e.getMessage());
			}
		} else {
			return ResponseEntity.ok("Default roles already exist. No further action required.");
		}
	}

}
