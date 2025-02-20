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
@Table(name = "user_answer")
public class UserAnswer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private MockExamQuestion mockExamQuestion;

	@Column(name = "selected_answer", length = 1, nullable = false)
	private String selectedAnswer;

	public UserAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAnswer(Long id, User user, MockExamQuestion mockExamQuestion, String selectedAnswer) {
		super();
		this.id = id;
		this.user = user;
		this.mockExamQuestion = mockExamQuestion;
		this.selectedAnswer = selectedAnswer;
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

	public MockExamQuestion getMockExamQuestion() {
		return mockExamQuestion;
	}

	public void setMockExamQuestion(MockExamQuestion mockExamQuestion) {
		this.mockExamQuestion = mockExamQuestion;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

}
