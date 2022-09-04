package com.qizz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qizz.model.User;
import com.qizz.repo.UserRepository;
//internally all the thing are taken care by spring security and we have to pass only the username
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		User user = this.userRepository.findByUsername(username);
		
		if(user==null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("User Credential are not correct");
		}
		return user;
	}
	
	
	

}
