package com.pbl5.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_result")
public class TestResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mock_exam_id", nullable = false)
	private MockExam mockExam;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "correct_answers", nullable = false)
	private Long correctAnswers;

	@Column(name = "incorrect_answers", nullable = false)
	private Long incorrectAnswers;

	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	public TestResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestResult(Long id, MockExam mockExam, User user, Long correctAnswers, Long incorrectAnswers,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.mockExam = mockExam;
		this.user = user;
		this.correctAnswers = correctAnswers;
		this.incorrectAnswers = incorrectAnswers;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MockExam getMockExam() {
		return mockExam;
	}

	public void setMockExam(MockExam mockExam) {
		this.mockExam = mockExam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(Long correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public Long getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public void setIncorrectAnswers(Long incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
