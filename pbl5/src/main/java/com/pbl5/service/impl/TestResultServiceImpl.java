package com.pbl5.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl5.model.TestResult;
import com.pbl5.model.User;
import com.pbl5.repository.TestResultRepository;
import com.pbl5.service.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private TestResultRepository testResultRepository;

	@Override
	public TestResult save(TestResult testResult) {
		try {
			return this.testResultRepository.save(testResult);
		} catch (Exception e) {
			logger.error("Lỗi khi lưu kết quả test: " + testResult, e);
			throw new RuntimeException("Không thể lưu kết quả test", e);
		}
	}

	@Override
	public List<TestResult> findByUser(User user) {
		try {
			return this.testResultRepository.findByUser(user);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách kết quả test của user: " + user, e);
			throw new RuntimeException("Không thể lấy danh sách kết quả test của user", e);
		}
	}

	@Override
	public long countAllTestResults() {
		try {
			return testResultRepository.count();
		} catch (Exception e) {
			logger.error("Lỗi khi đếm tổng số kết quả bài thi thử", e);
			return -1;
		}
	}

	@Override
	public List<TestResult> findTop10ByOrderByCorrectAnswersDesc() {
		try {
			return testResultRepository.findTop10ByOrderByCorrectAnswersDesc();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy top 10 kết quả test theo số câu đúng giảm dần", e);
			return List.of();
		}
	}

	@Override
	public Double getAverageScore() {
		try {
			return testResultRepository.findAverageScore();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy điểm trung bình", e);
			return null;
		}
	}

	@Override
	public Long getMaxScore() {
		try {
			return testResultRepository.findMaxScore();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy điểm cao nhất", e);
			return null;
		}
	}

	@Override
	public Long getMinScore() {
		try {
			return testResultRepository.findMinScore();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy điểm thấp nhất", e);
			return null;
		}
	}

	@Override
	public Long getMostAttemptedMockExamId() {
		try {
			List<Long> ids = testResultRepository.findMostAttemptedMockExamIds();
			return ids.isEmpty() ? null : ids.get(0);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy ID đề thi được làm nhiều nhất", e);
			return null;
		}
	}

	@Override
	public Map<Integer, Double> getAverageScoresByDayInCurrentMonth() {
		try {
			LocalDate now = LocalDate.now();
			YearMonth yearMonth = YearMonth.from(now);
			LocalDate start = yearMonth.atDay(1);
			LocalDate end = yearMonth.atEndOfMonth();

			List<TestResult> results = testResultRepository.findByCreatedAtBetween(start.atStartOfDay(),
					end.atTime(LocalTime.MAX));

			return results.stream().collect(Collectors.groupingBy(r -> r.getCreatedAt().getDayOfMonth(), TreeMap::new,
					Collectors.averagingDouble(r -> (double) r.getCorrectAnswers())));
		} catch (Exception e) {
			logger.error("Lỗi khi tính số câu đúng trung bình theo ngày trong tháng hiện tại", e);
			return Map.of(); // trả về map rỗng nếu có lỗi
		}
	}

}
