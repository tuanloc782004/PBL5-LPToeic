package com.pbl5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbl5.model.VocabularyLesson;

public interface VocabularyLessonService {

	public List<VocabularyLesson> findByKeyword(String keyword);

	public Page<VocabularyLesson> findAll(Integer pageno);

	public Page<VocabularyLesson> findByKeyword(String keyword, Integer pageNo);

	public void deleteById(Long id);

	public VocabularyLesson save(VocabularyLesson vocabularyLesson);
	
	public List<VocabularyLesson> findAll();
	
	public VocabularyLesson findById(Long id);
	
	public VocabularyLesson findFirstLesson();
	
	public long countAllVocabularyLessons(); 
	
}
