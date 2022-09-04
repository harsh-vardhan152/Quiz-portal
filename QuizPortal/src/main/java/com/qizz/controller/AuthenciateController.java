package com.qizz.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qizz.config.JwtUtils;
import com.qizz.model.JwtRequest;
import com.qizz.model.JwtResponse;
import com.qizz.model.User;
import com.qizz.service.impl.UserDetailsServiceImpl;

@RestController
public class AuthenciateController {
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	//to generated the token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Usernot found exception");
		}
		
		//user is now authenticated
		
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	
	
	private void authenticate(String username,String password) throws Exception {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {
			
			throw new Exception("USER DISABLE"+e.getMessage());
		}
		catch(BadCredentialsException e) {
			 
			throw new Exception("Invalid Credentials"+e.getMessage());
			
		}
		
	}
	@GetMapping("/current-user")
	public User getCurrentuser(Principal principal) {
		//typecasting to user
		return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
	}

}
