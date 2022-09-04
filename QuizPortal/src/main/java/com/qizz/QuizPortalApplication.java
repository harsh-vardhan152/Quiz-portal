package com.qizz;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.qizz.model.Role;
import com.qizz.model.User;
import com.qizz.model.UserRole;
import com.qizz.service.UserService;

@SpringBootApplication
public class QuizPortalApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(QuizPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("start code");
		
//		User user=new User();
//		user.setFirstname("Harsh");
//		user.setLastname("vardhan");
//		user.setUsername("harsh29271");
//		user.setPassword(this.bCryptPasswordEncoder.encode("Harsh@123"));
//		user.setEmail("harsh.vardhan_cs18@gla.ac.in");
//		user.setProfile("default.png");
//		
//		Role role1 = new Role();
//		role1.setRoleId(44L);
//		role1.setRolename("ADMIN");
//		
//		Set<UserRole> userRoleSet=new HashSet<>();
//		UserRole userRole=new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//		User user1=this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());
	}

}
