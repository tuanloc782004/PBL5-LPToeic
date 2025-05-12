package com.pbl5.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl5.model.TestResult;
import com.pbl5.model.User;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {

	List<TestResult> findByUser(User user);

	long count();

	List<TestResult> findTop10ByOrderByCorrectAnswersDesc();

	@Query("SELECT AVG(tr.correctAnswers) FROM TestResult tr")
	Double findAverageScore();

	@Query("SELECT MAX(tr.correctAnswers) FROM TestResult tr")
	Long findMaxScore();

	@Query("SELECT MIN(tr.correctAnswers) FROM TestResult tr")
	Long findMinScore();

	@Query("SELECT tr.mockExam.id FROM TestResult tr GROUP BY tr.mockExam.id ORDER BY COUNT(tr.id) DESC")
	List<Long> findMostAttemptedMockExamIds();

	List<TestResult> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
