package com.pbl5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbl5.model.Grammar;

public interface GrammarService {

	public List<Grammar> findByKeyword(String keyword);

	public Page<Grammar> findAll(Integer pageno);

	public Page<Grammar> findByKeyword(String keyword, Integer pageNo);

	public void deleteById(Long id);

	public Grammar save(Grammar grammar);
	
}
