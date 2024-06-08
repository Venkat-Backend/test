package com.spantag.springsecurity.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.spantag.springsecurity.dto.RefreshTokenRequest;
import com.spantag.springsecurity.entity.User;

public interface JWTService {
	
	String extractUserName(String token);
	
	String generateToken(UserDetails userDetails);
	
	boolean isTokenValid(String token, UserDetails userDetails);

	String generateRefreshToken(Map<String, Object> extraClaims,UserDetails userDetails);

	

}
