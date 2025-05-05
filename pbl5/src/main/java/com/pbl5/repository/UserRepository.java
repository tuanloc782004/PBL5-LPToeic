package com.pbl5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl5.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username LIKE %?1% OR u.email LIKE %?1% OR u.role.roleName LIKE %?1%")
    List<User> findByKeyword(String keyword);
	
	User findByEmail(String email);
	
	long count();
	
}
