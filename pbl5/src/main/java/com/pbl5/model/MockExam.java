package com.pbl5.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mock_exam")
public class MockExam {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "image_url", length = 255, columnDefinition = "VARCHAR(255) DEFAULT '/image/mock_exam.jpg'")
	private String imageUrl;
	
	@Column(name = "mock_exam_name", nullable = false, length = 255)
	private String mockExamName;
	
	@OneToMany(mappedBy = "mockExam", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<MockExamQuestion> mockExamQuestions;
	
	@OneToMany(mappedBy = "mockExam", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<TestResult> testResults;

	public MockExam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MockExam(Long id, String imageUrl, String mockExamName, List<MockExamQuestion> mockExamQuestions,
			List<TestResult> testResults) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.mockExamName = mockExamName;
		this.mockExamQuestions = mockExamQuestions;
		this.testResults = testResults;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMockExamName() {
		return mockExamName;
	}

	public void setMockExamName(String mockExamName) {
		this.mockExamName = mockExamName;
	}

	public List<MockExamQuestion> getMockExamQuestions() {
		return mockExamQuestions;
	}

	public void setMockExamQuestions(List<MockExamQuestion> mockExamQuestions) {
		this.mockExamQuestions = mockExamQuestions;
	}

	public List<TestResult> getTestResults() {
		return testResults;
	}

	public void setTestResults(List<TestResult> testResults) {
		this.testResults = testResults;
	}
	
}
