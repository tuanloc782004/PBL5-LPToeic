package com.pbl5.dto;

import java.util.List;

public class Part7DTO {

	private String script;

	private List<Part7QuestionDTO> questions;

	public static class Part7QuestionDTO {

		private String question;
		private String optionA;
		private String optionB;
		private String optionC;
		private String optionD;
		private String correctAnswer;
		private String explanation;

		public Part7QuestionDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Part7QuestionDTO(String question, String optionA, String optionB, String optionC, String optionD,
				String correctAnswer, String explanation) {
			super();
			this.question = question;
			this.optionA = optionA;
			this.optionB = optionB;
			this.optionC = optionC;
			this.optionD = optionD;
			this.correctAnswer = correctAnswer;
			this.explanation = explanation;
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

	public Part7DTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Part7DTO(String script, List<Part7QuestionDTO> questions) {
		super();
		this.script = script;
		this.questions = questions;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public List<Part7QuestionDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Part7QuestionDTO> questions) {
		this.questions = questions;
	}

}
