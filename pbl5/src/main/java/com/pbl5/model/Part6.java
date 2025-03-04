package com.pbl5.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "part6")
public class Part6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mock_exam_id")
    private MockExam mockExam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reading_exercise_id")
    private ReadingExercise readingExercise;

    @Column(name = "script", nullable = false, columnDefinition = "TEXT")
    private String script;
    
    @OneToMany(mappedBy = "part6", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part6Question> questions;

	public Part6() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Part6(Long id, MockExam mockExam, ReadingExercise readingExercise, String script,
			List<Part6Question> questions) {
		super();
		this.id = id;
		this.mockExam = mockExam;
		this.readingExercise = readingExercise;
		this.script = script;
		this.questions = questions;
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

	public ReadingExercise getReadingExercise() {
		return readingExercise;
	}

	public void setReadingExercise(ReadingExercise readingExercise) {
		this.readingExercise = readingExercise;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public List<Part6Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Part6Question> questions) {
		this.questions = questions;
	}

}
