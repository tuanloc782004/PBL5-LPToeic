package com.pbl5.service;

import java.util.List;
import java.util.Map;

import com.pbl5.model.TestResult;
import com.pbl5.model.User;

public interface TestResultService {

	public TestResult save(TestResult testResult);

	public List<TestResult> findByUser(User user);

	public long countAllTestResults();

	public List<TestResult> findTop10ByOrderByCorrectAnswersDesc();

	public Double getAverageScore();

	public Long getMaxScore();

	public Long getMinScore();

	public Long getMostAttemptedMockExamId();

	public Map<Integer, Double> getAverageScoresByDayInCurrentMonth();

}
