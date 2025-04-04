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
@Table(name = "part3_question")
public class Part3Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "part3_id", nullable = false)
	private Part3 part3;
	
	@Column(name = "number", nullable = false)
	private Long number;

	@Column(name = "question", nullable = false, length = 255)
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

	@Column(name = "explanation", nullable = false, length = 255)
	private String explanation;

	public Part3Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Part3Question(Long id, Part3 part3, Long number, String question, String optionA, String optionB,
			String optionC, String optionD, String correctAnswer, String explanation) {
		super();
		this.id = id;
		this.part3 = part3;
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

	public Part3 getPart3() {
		return part3;
	}

	public void setPart3(Part3 part3) {
		this.part3 = part3;
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
