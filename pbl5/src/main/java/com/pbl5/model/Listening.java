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
@Table(name = "listening")
public class Listening {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "part_id", nullable = false)
	private Part part;

	@Column(name = "listening_name", nullable = false, length = 255)
	private String listeningName;

	@Column(name = "difficulty")
	private Long difficulty;

	@Column(name = "script", length = 255)
	private String script;

	@OneToMany(mappedBy = "listening", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ListeningQuestion> listeningQuestions;

	public Listening() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Listening(Long id, Part part, String listeningName, Long difficulty, String script,
			List<ListeningQuestion> listeningQuestions) {
		super();
		this.id = id;
		this.part = part;
		this.listeningName = listeningName;
		this.difficulty = difficulty;
		this.script = script;
		this.listeningQuestions = listeningQuestions;
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

	public String getListeningName() {
		return listeningName;
	}

	public void setListeningName(String listeningName) {
		this.listeningName = listeningName;
	}

	public Long getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Long difficulty) {
		this.difficulty = difficulty;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public List<ListeningQuestion> getListeningQuestions() {
		return listeningQuestions;
	}

	public void setListeningQuestions(List<ListeningQuestion> listeningQuestions) {
		this.listeningQuestions = listeningQuestions;
	}

}
