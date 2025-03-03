package com.pbl5.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl5.model.VocabularyLesson;

public interface VocabularyLessonRepository extends JpaRepository<VocabularyLesson, Long> {

	@Query("SELECT vl FROM VocabularyLesson vl WHERE vl.lessonName LIKE %?1%")
	List<VocabularyLesson> findByKeyword(String keyword);

}
