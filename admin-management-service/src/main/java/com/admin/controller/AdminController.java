package com.admin.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.AuthenticationRequest;
import com.admin.dto.AuthenticationResponse;
import com.admin.dto.LoginRequest;
import com.admin.dto.LoginResponse;
import com.admin.filter.JwtRequestFilter;
import com.admin.pojo.Admin;
import com.admin.pojo.AuthenticationJwtResponse;
import com.admin.service.AdminService;
import com.admin.service.MyUserDetailsService;
import com.admin.service.SequenceGeneratorService;
import com.admin.util.JwtUtil;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		
		logger.info("jwtAuthenticate method from Admin controller is accessed");
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
	

	@PostMapping("/add")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
		
		//logger.info("addAdmin method from Admin controller is accessed");

		admin.setAdminId(sequenceGeneratorService.generateSequence(Admin.SEQUENCE_NAME));

		Admin newAdmin = adminService.saveAdmin(admin);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<>(newAdmin, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/all")
	public List<Admin> fetchAllAdmin() {
		
		logger.info("fetchAllAdmin method from Admin controller is accessed");

		List<Admin> allAdmin = adminService.getAllAdmin();
		return allAdmin;
	}

	@GetMapping("/find/{adminId}")
	public ResponseEntity<Admin> fetchById(@PathVariable("adminId") int adminId) {
		
		logger.info("AdminById method from Admin controller is accessed");

		ResponseEntity<Admin> reponseEntity = null;
		Admin admin = adminService.getAdminById(adminId);
		reponseEntity = new ResponseEntity<>(admin, HttpStatus.OK);
		return reponseEntity;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginRequest) {
		
		logger.info("signIn method from Admin controller is accessed");

		Admin admin = adminService.doLogin(loginRequest.getUserName(), loginRequest.getPassword());

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setAdminId(admin.getAdminId());
		loginResponse.setFirstName(admin.getFirstName());
		loginResponse.setLastName(admin.getLastName());
		loginResponse.setPhone(admin.getPhone());
		loginResponse.setUserName(admin.getUserName());
		loginResponse.setEmail(admin.getEmail());
		ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(loginResponse, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/update")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
		
		logger.info("updateAdmin method from Admin controller is accessed");

		Admin updatedAdmin = adminService.updateAdmin(admin);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/delete/{adminId}")
	public ResponseEntity<String> deleteAdminById(@PathVariable("adminId") int adminId) {
		
		logger.info("deleteAdmin method from Admin controller is accessed");

		adminService.deleteAdminById(adminId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/bylocation/{location}")
	public List<Admin> fetchAdminbyLocation(@PathVariable("location") String location) {
		
		logger.info("AdminBylocation method from Admin controller is accessed");

		List<Admin> adminByLocation = adminService.getAllAdminByLocation(location);
		return adminByLocation;
	}

	@GetMapping("/byusername/{username}")
	public Optional<Admin> fetchAdminbyUserName(@PathVariable("username") String userName) {
		
		logger.info("AdminByUsername method from Admin controller is accessed");

		Optional<Admin> adminByUserName = adminService.getAllAdminByUserName(userName);
		return adminByUserName;
	}

	@GetMapping("/byphone/{phone}")
	public Optional<Admin> fetchAdminbyPhone(@PathVariable("phone") long phone) {
		
		logger.info("AdminByPhone method from Admin controller is accessed");

		Optional<Admin> adminByPhone = adminService.getAllAdminByPhone(phone);
		return adminByPhone;
	}

}
