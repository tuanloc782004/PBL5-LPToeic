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
@Table(name = "part4_question")
public class Part4Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "part4_id", nullable = false)
	private Part4 part4;

	@Column(name = "number", nullable = false)
	private Long number;

	@Column(name = "question", nullable = false, columnDefinition = "TEXT")
	private String question;

	@Column(name = "option_a", nullable = false, length = 255)
	private String optionA;

	@Column(name = "option_b", nullable = false, length = 255)
	private String optionB;

	@Column(name = "option_c", nullable = false, length = 255)
	private String optionC;

	@Column(name = "option_d", nullable = false, length = 255)
	private String optionD;

	@Column(name = "correct_answer", nullable = false, length = 255)
	private String correctAnswer;

	@Column(name = "explanation", columnDefinition = "TEXT", nullable = false)
	private String explanation;

	public Part4Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Part4Question(Long id, Part4 part4, Long number, String question, String optionA, String optionB,
			String optionC, String optionD, String correctAnswer, String explanation) {
		super();
		this.id = id;
		this.part4 = part4;
		this.number = number;
		this.question = question;
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

	public Part4 getPart4() {
		return part4;
	}

	public void setPart4(Part4 part4) {
		this.part4 = part4;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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
