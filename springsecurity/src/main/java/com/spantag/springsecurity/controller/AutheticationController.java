package com.spantag.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spantag.springsecurity.dto.JwtAutheticationResponse;
import com.spantag.springsecurity.dto.RefreshTokenRequest;
import com.spantag.springsecurity.dto.SignUpRequest;
import com.spantag.springsecurity.dto.SigninRequest;
import com.spantag.springsecurity.entity.User;
import com.spantag.springsecurity.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AutheticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
		return ResponseEntity.ok(authenticationService.signup(signUpRequest));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAutheticationResponse> signin(@RequestBody SigninRequest signinRequest){
		return ResponseEntity.ok(authenticationService.signin(signinRequest));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JwtAutheticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
	}
	@PostMapping("/super")
	public ResponseEntity<User> superadmin(@RequestBody SignUpRequest signUpRequest){
		return ResponseEntity.ok(authenticationService.superadmin(signUpRequest));
	}

}
