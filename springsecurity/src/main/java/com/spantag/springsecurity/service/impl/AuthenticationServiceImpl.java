package com.spantag.springsecurity.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.spantag.springsecurity.dto.JwtAutheticationResponse;
import com.spantag.springsecurity.dto.RefreshTokenRequest;
import com.spantag.springsecurity.dto.SignUpRequest;
import com.spantag.springsecurity.dto.SigninRequest;
import com.spantag.springsecurity.entity.Role;
import com.spantag.springsecurity.entity.User;
import com.spantag.springsecurity.repository.UserRepository;
import com.spantag.springsecurity.service.AuthenticationService;
import com.spantag.springsecurity.service.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
    
	@Autowired
	private AuthenticationManager authenticationManager;
    
	@Autowired
	private JWTService  jwtService;
	
	
	public User signup(SignUpRequest signUpRequest) {
		User user = new User();
		
		user.setEmail(signUpRequest.getEmail());
		user.setFirstname(signUpRequest.getFirstName());
		user.setSecondname(signUpRequest.getLastName());
		user.setRole(Role.USER);
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		return userRepository.save(user);
		
	}
	public User superadmin(SignUpRequest signUpRequest) {
		User user = new User();
		
		user.setEmail(signUpRequest.getEmail());
		user.setFirstname(signUpRequest.getFirstName());
		user.setSecondname(signUpRequest.getLastName());
		user.setRole(Role.SUPERADMIN);
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		return userRepository.save(user);
		
	}
	
	public JwtAutheticationResponse signin(SigninRequest signinRequest) {
		
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
		
		
		var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password,"));
		var jwt = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
		
		JwtAutheticationResponse jwtAutheticationResponse = new JwtAutheticationResponse();
		
		jwtAutheticationResponse.setToken(jwt);
		jwtAutheticationResponse.setRefreshToken(refreshToken);
		
		
		return jwtAutheticationResponse;
		
	}
	
	public JwtAutheticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail).orElseThrow();
		if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
			
		var jwt = jwtService.generateToken(user);	
		
JwtAutheticationResponse jwtAutheticationResponse = new JwtAutheticationResponse();
		
		jwtAutheticationResponse.setToken(jwt);
		jwtAutheticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			
		return jwtAutheticationResponse;		
		}
		return null;
	}

}
