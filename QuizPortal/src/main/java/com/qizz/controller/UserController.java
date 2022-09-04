package com.qizz.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qizz.model.Role;
import com.qizz.model.User;
import com.qizz.model.UserRole;
import com.qizz.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//Creating User
	@PostMapping("/")
	public User createuser(@RequestBody User user) throws Exception {
		
		user.setProfile("harsh.png");
		
//		when i use bcryptpassword encoder then i have to store password in encrypted form
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles =new HashSet<>();
		
		Role role=new Role();
		role.setRoleId(45L);
		role.setRolename("NORMAL");
		
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);
		
		return this.userService.createUser(user,roles);
		
	}
	
	//get the user
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username")String username) {
		return this.userService.getUser(username);
	}
	
	//delete the user by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")Long userId) {
		this.userService.deleteUser(userId);
	}
	
	//update user
	@PutMapping("/")
	public User updateUser(@RequestBody User user) {
		return this.userService.updateUser(user);
	}

}
