package com.pbl5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbl5.model.Part3;
import com.pbl5.model.User;

public interface UserService {

	public User findByUsername(String username); 
		
	public List<User> findByKeyword(String keyword);
	
	public Page<User> findAll(Integer pageno);

	public Page<User> findByKeyword(String keyword, Integer pageNo);
	
	public void deleteById(Long id);
  
	public User findByEmail(String email); 
	
	public User save(User user);

}
