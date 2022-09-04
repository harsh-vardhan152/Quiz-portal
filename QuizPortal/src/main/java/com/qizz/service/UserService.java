package com.qizz.service;

import java.util.Set;

import com.qizz.model.User;
import com.qizz.model.UserRole;

public interface UserService {
	
	//creating the user 
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	//getting user
	public User getUser(String username);
	
	//delete the by id
	public void deleteUser(Long userId);

	public User updateUser(User user);
	

}
