package com.pbl5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl5.model.GrammarLesson;

public interface GrammarLessonRepository extends JpaRepository<GrammarLesson, Long> {

	@Query("SELECT gl FROM GrammarLesson gl WHERE gl.lessonName LIKE %?1%")
	List<GrammarLesson> findByKeyword(String keyword);
	
	@Query("SELECT gl FROM GrammarLesson gl ORDER BY gl.id ASC LIMIT 1")
	GrammarLesson findFirstLesson();
	
	long count();
	
}
