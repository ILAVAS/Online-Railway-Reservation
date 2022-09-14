package com.user.service.Impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.exception.AuthenticationFailedException;
import com.user.exception.UserNameAlreadyExistingException;
import com.user.exception.UserNotFoundException;
import com.user.models.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	public static Logger logAdmin = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepo;
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;


	@Override
	public User addUser(User user) throws UserNameAlreadyExistingException {

		if (this.userRepo.existsByUserName(user.getUserName())) {
			
			logAdmin.error("User already exist try new one");

			throw new UserNameAlreadyExistingException("UserName already exist try new one");
		}
		
		String encryptedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPwd);
				
		return userRepo.save(user);

	}
	@Override
	public User doLogin(String userName, String password) {

		User user = userRepo.login(userName, password);
		if (user == null) {
			throw new AuthenticationFailedException("Username or password are invalid");
		}
		return user;
	}

	@Override
	public List<User> viewAllUsers() throws UserNotFoundException {

		List<User> user = userRepo.findAll();
		if (user == null) {
			throw new UserNotFoundException("User not found");
		} else

			return user;
	}

	@Override
	public User viewUser(String id) throws UserNotFoundException {

		Optional<User> user = userRepo.findById(id);

		User u = null;
		if (user.isPresent()) {

			u = user.get();
		} else {
			throw new UserNotFoundException("No such User");
		}
		return u;
	}
	
	@Override
	public String deleteUser(String id) throws UserNotFoundException {
		
		String message = null;
		Optional<User> user=userRepo.findById(id);
		if (user.isPresent()) {
			userRepo.deleteById(id);
			message = "Deleted Successfully";
			logAdmin.info(message);
		}
		else {
			message = "User Not found";
			logAdmin.error(message);
		}
		return message;
	}

	@Override
	public User updateUser( User user) throws UserNotFoundException {
		Optional<User> u = userRepo.findById(user.getId());

		User userRecord = null;
		if (u.isPresent()) {
		  userRecord = u.get();
			userRepo.save(user);
		} else {
			logAdmin.error("User not found");
		}
		return userRecord;
	}

	

}