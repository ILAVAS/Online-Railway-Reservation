package com.user.service;

import java.util.List;

import com.user.exception.UserNameAlreadyExistingException;
import com.user.exception.UserNotFoundException;
import com.user.models.User;

public interface UserService {

	public User addUser(User user) throws UserNameAlreadyExistingException;
	
	public User doLogin(String userName, String password) ;

	public User viewUser(String id) throws UserNotFoundException;

	public String deleteUser(String id) throws UserNotFoundException;

	public User updateUser(User user) throws UserNotFoundException;

	public List<User> viewAllUsers() throws UserNotFoundException;

}