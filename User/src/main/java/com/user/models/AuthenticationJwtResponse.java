package com.user.models;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationJwtResponse {
	private  String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	private UserDetails userDetails;

	public AuthenticationJwtResponse(String jwt, UserDetails userDetails) {
		super();
		this.jwt = jwt;
		this.userDetails = userDetails;
	}

	public AuthenticationJwtResponse() {
		//super();
		// TODO Auto-generated constructor stub
	}

}
