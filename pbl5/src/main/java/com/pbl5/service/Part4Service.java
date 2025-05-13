package com.pbl5.service;

import java.util.List;

import com.pbl5.model.Part4;

public interface Part4Service {

	public Part4 save(Part4 part4);

	public List<Part4> findAll();

	public Part4 findById(Long id);

}
