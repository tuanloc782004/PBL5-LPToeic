package com.pbl5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl5.model.MockExam;

public interface MockExamRepository extends JpaRepository<MockExam, Long> {
	
	@Query("SELECT m FROM MockExam m WHERE m.mockExamName LIKE %?1%")
	List<MockExam> findByKeyword(String keyword);
	
	long count();

}
