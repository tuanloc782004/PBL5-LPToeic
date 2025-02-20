package com.pbl5.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reading")
public class Reading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "part_id", nullable = false)
	private Part part;

	@Column(name = "reading_name", nullable = false, length = 255)
	private String readingName;

	@Column(name = "difficulty")
	private long difficulty;

	@Column(name = "script", length = 255)
	private String script;

	@OneToMany(mappedBy = "reading", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReadingQuestion> readingQuestions;

	public Reading() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reading(Long id, Part part, String readingName, long difficulty, String script,
			List<ReadingQuestion> readingQuestions) {
		super();
		this.id = id;
		this.part = part;
		this.readingName = readingName;
		this.difficulty = difficulty;
		this.script = script;
		this.readingQuestions = readingQuestions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public String getReadingName() {
		return readingName;
	}

	public void setReadingName(String readingName) {
		this.readingName = readingName;
	}

	public long getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(long difficulty) {
		this.difficulty = difficulty;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public List<ReadingQuestion> getReadingQuestions() {
		return readingQuestions;
	}

	public void setReadingQuestions(List<ReadingQuestion> readingQuestions) {
		this.readingQuestions = readingQuestions;
	}
	
}
