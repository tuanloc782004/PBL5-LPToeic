package com.pbl5.service;

import java.util.List;

import com.pbl5.model.Part7;

public interface Part7Service {

	public Part7 save(Part7 part7);

	public List<Part7> findAll();

	public Part7 findById(Long id);
}
