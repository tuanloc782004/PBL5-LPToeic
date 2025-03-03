package com.pbl5.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reading_exercise")
public class ReadingExercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "exercise_name", nullable = false, length = 255)
	private String exerciseName;
	
	@OneToMany(mappedBy = "readingExercise", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part5> part5s;
	
	@OneToMany(mappedBy = "readingExercise", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part6> part6s;
	
	@OneToMany(mappedBy = "readingExercise", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part7> part7s;

	public ReadingExercise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReadingExercise(Long id, String exerciseName, List<Part5> part5s, List<Part6> part6s, List<Part7> part7s) {
		super();
		this.id = id;
		this.exerciseName = exerciseName;
		this.part5s = part5s;
		this.part6s = part6s;
		this.part7s = part7s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public List<Part5> getPart5s() {
		return part5s;
	}

	public void setPart5s(List<Part5> part5s) {
		this.part5s = part5s;
	}

	public List<Part6> getPart6s() {
		return part6s;
	}

	public void setPart6s(List<Part6> part6s) {
		this.part6s = part6s;
	}

	public List<Part7> getPart7s() {
		return part7s;
	}

	public void setPart7s(List<Part7> part7s) {
		this.part7s = part7s;
	}
	
}
