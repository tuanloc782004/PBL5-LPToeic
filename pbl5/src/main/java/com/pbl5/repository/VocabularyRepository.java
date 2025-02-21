package com.pbl5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl5.model.Vocabulary;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

	@Query("SELECT v FROM Vocabulary v WHERE v.vocabularyName LIKE %?1%")
    List<Vocabulary> findByKeyword(String keyword);
	
}
