package com.pbl5.model;

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
@Table(name = "part1")
public class Part1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mock_exam_id")
	private MockExam mockExam;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "listening_exercise_id")
	private ListeningExercise listeningExercise;

	@Column(name = "number", nullable = false)
	private Long number;

	@Column(name = "image_url", nullable = false, length = 255)
	private String imageUrl;

	@Column(name = "audio_url", nullable = false, length = 255)
	private String audioUrl;

	@Column(name = "option_a", nullable = false, length = 255)
	private String optionA;

	@Column(name = "option_b", nullable = false, length = 255)
	private String optionB;

	@Column(name = "option_c", nullable = false, length = 255)
	private String optionC;

	@Column(name = "option_d", nullable = false, length = 255)
	private String optionD;

	@Column(name = "correct_answer", nullable = false, length = 1)
	private String correctAnswer;

	@Column(name = "explanation", columnDefinition = "TEXT", nullable = false)
	private String explanation;

	public Part1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Part1(Long id, MockExam mockExam, ListeningExercise listeningExercise, Long number, String imageUrl,
			String audioUrl, String optionA, String optionB, String optionC, String optionD, String correctAnswer,
			String explanation) {
		super();
		this.id = id;
		this.mockExam = mockExam;
		this.listeningExercise = listeningExercise;
		this.number = number;
		this.imageUrl = imageUrl;
		this.audioUrl = audioUrl;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.correctAnswer = correctAnswer;
		this.explanation = explanation;
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

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

}
