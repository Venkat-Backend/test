package com.spantag.springsecurity.service;

import com.spantag.springsecurity.dto.JwtAutheticationResponse;
import com.spantag.springsecurity.dto.RefreshTokenRequest;
import com.spantag.springsecurity.dto.SignUpRequest;
import com.spantag.springsecurity.dto.SigninRequest;
import com.spantag.springsecurity.entity.User;

public interface AuthenticationService {
	
	User signup(SignUpRequest signUpRequest);
	
	User superadmin(SignUpRequest signUpRequest);
	
	
	JwtAutheticationResponse signin(SigninRequest signinRequest);
	
	JwtAutheticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
