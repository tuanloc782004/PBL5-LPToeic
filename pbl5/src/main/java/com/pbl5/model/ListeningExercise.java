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
@Table(name = "listening_exercise")
public class ListeningExercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "exercise_name", nullable = false, length = 255)
	private String exerciseName;

	@OneToMany(mappedBy = "listeningExercise", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part1> part1s;

	@OneToMany(mappedBy = "listeningExercise", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part2> part2s;

	@OneToMany(mappedBy = "listeningExercise", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part3> part3s;

	@OneToMany(mappedBy = "listeningExercise", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part4> part4s;

	public ListeningExercise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListeningExercise(Long id, String exerciseName, List<Part1> part1s, List<Part2> part2s, List<Part3> part3s,
			List<Part4> part4s) {
		super();
		this.id = id;
		this.exerciseName = exerciseName;
		this.part1s = part1s;
		this.part2s = part2s;
		this.part3s = part3s;
		this.part4s = part4s;
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

	public List<Part1> getPart1s() {
		return part1s;
	}

	public void setPart1s(List<Part1> part1s) {
		this.part1s = part1s;
	}

	public List<Part2> getPart2s() {
		return part2s;
	}

	public void setPart2s(List<Part2> part2s) {
		this.part2s = part2s;
	}

	public List<Part3> getPart3s() {
		return part3s;
	}

	public void setPart3s(List<Part3> part3s) {
		this.part3s = part3s;
	}

	public List<Part4> getPart4s() {
		return part4s;
	}

	public void setPart4s(List<Part4> part4s) {
		this.part4s = part4s;
	}

}
