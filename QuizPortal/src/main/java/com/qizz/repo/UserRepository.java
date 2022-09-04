package com.qizz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qizz.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
	
	

}
