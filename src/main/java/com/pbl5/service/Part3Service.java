package com.pbl5.service;

import java.util.List;

import com.pbl5.model.Part3;

public interface Part3Service {

	public Part3 save(Part3 part3);

	public List<Part3> findAll();

	public Part3 findById(Long id);

}
