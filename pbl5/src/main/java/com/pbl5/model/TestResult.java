package com.pbl5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "mock_exam_id", referencedColumnName = "id", nullable = false)
    private MockExam mockExam;
	
	@Column(name = "correct_answers", nullable = false)
	private Long correctAnswers;
	
	@Column(name = "incorrect_answers", nullable = false)
	private Long incorrectAnswers;

	public TestResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestResult(Long id, User user, MockExam mockExam, Long correctAnswers, Long incorrectAnswers) {
		super();
		this.id = id;
		this.user = user;
		this.mockExam = mockExam;
		this.correctAnswers = correctAnswers;
		this.incorrectAnswers = incorrectAnswers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MockExam getMockExam() {
		return mockExam;
	}

	public void setMockExam(MockExam mockExam) {
		this.mockExam = mockExam;
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
	
}
