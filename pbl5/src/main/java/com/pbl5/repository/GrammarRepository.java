package com.pbl5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl5.model.Grammar;

public interface GrammarRepository extends JpaRepository<Grammar, Long> {

	@Query("SELECT g FROM Grammar g WHERE g.grammarName LIKE %?1%")
    List<Grammar> findByKeyword(String keyword);
	
}
