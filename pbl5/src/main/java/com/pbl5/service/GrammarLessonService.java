package com.pbl5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbl5.model.GrammarLesson;

public interface GrammarLessonService {

	public List<GrammarLesson> findByKeyword(String keyword);

	public Page<GrammarLesson> findAll(Integer pageno);

	public Page<GrammarLesson> findByKeyword(String keyword, Integer pageNo);

	public void deleteById(Long id);

	public GrammarLesson save(GrammarLesson grammarLesson);
	
	public GrammarLesson findById(Long id);
	
	public List<GrammarLesson> findAll(); 
	
	public GrammarLesson findFirstLesson();

}
