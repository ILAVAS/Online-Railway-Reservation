package com.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.exception.UserNameAlreadyExistingException;
import com.user.jwtutil.JwtUtil;
import com.user.models.AuthenticationJwtResponse;
import com.user.models.AuthenticationRequest;
import com.user.models.AuthenticationResponse;
import com.user.models.User;
import com.user.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	
	
	// user registration form 
	
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user) throws UserNameAlreadyExistingException {
		return userService.addUser(user);

	}


	
	// >>>login form
		@PostMapping("/login")
		private ResponseEntity<?> authenticateClient (@RequestBody AuthenticationRequest authenticationRequest){
			
			String userName = authenticationRequest.getUserName();
			
			String password = authenticationRequest.getPassword();
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			}
			
			catch (Exception e) {
				return ResponseEntity.ok(new AuthenticationResponse("Error during client Authentication "+ userName));
				
			}
			
			final UserDetails userDetails =  userDetailsService.loadUserByUsername(userName);
			
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			
			// User user = user.findById(userName).get();
			
			return ResponseEntity.ok(new AuthenticationJwtResponse(jwt,userDetails));
			}
		
	
	// View all user

				@GetMapping("/all")
				public List<User> fetchAllUser() {		
					return userService.viewAllUsers();

				}
				
   //update user
				@PutMapping("/update")
				public ResponseEntity<User> updateUser(@RequestBody User user) {
					
					logger.info("updateUser method from User controller is accessed");

					User updatedUser = userService.updateUser(user);
					ResponseEntity<User> responseEntity = new ResponseEntity<>(updatedUser, HttpStatus.OK);
					return responseEntity;
				}
//delete user
	
				@DeleteMapping("/delete/{id}")
				public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
					
					logger.info("deleteUser method from User controller is accessed");

					userService.deleteUser(id);
					ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
					return responseEntity;
				}
				
				
				 

}
