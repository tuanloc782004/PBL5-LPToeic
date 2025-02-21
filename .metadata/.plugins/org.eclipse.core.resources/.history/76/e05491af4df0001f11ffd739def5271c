package com.pbl5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbl5.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
