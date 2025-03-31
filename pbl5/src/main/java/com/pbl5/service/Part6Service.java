package com.pbl5.service;

import java.util.List;

import com.pbl5.model.Part6;

public interface Part6Service {

	public Part6 save(Part6 part6);
	
	public List<Part6> findAll();
	
	public Part6 findById(Long id);

}
