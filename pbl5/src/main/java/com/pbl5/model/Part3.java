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
@Table(name = "part3")
public class Part3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mock_exam_id")
	private MockExam mockExam;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "listening_exercise_id")
	private ListeningExercise listeningExercise;

	@Column(name = "audio_url", nullable = false, length = 255)
	private String audioUrl;

	@OneToMany(mappedBy = "part3", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part3Question> questions;

	public Part3() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Part3(Long id, MockExam mockExam, ListeningExercise listeningExercise, String audioUrl,
			List<Part3Question> questions) {
		super();
		this.id = id;
		this.mockExam = mockExam;
		this.listeningExercise = listeningExercise;
		this.audioUrl = audioUrl;
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

	public ListeningExercise getListeningExercise() {
		return listeningExercise;
	}

	public void setListeningExercise(ListeningExercise listeningExercise) {
		this.listeningExercise = listeningExercise;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public List<Part3Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Part3Question> questions) {
		this.questions = questions;
	}
	
}
