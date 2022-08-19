package com.admin.pojo;

public class AuthenticationJwtResponse {

	 private final String jwt;



	   
	    public AuthenticationJwtResponse(String jwt) {
	        super();
	        this.jwt = jwt;
	    }



	   public String getJwt() {
	        return jwt;
	    }
	    
	    
}
