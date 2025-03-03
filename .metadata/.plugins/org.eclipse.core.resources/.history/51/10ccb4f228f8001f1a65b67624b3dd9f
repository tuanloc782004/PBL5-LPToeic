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
@Table(name = "vocabulary_lesson")
public class VocabularyLesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "lesson_name", nullable = false, length = 255)
	private String lessonName;
	
	@OneToMany(mappedBy = "vocabulary", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VocabularyLessonContent> vocabularyLessonContents;

	public VocabularyLesson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VocabularyLesson(Long id, String lessonName, List<VocabularyLessonContent> vocabularyLessonContents) {
		super();
		this.id = id;
		this.lessonName = lessonName;
		this.vocabularyLessonContents = vocabularyLessonContents;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public List<VocabularyLessonContent> getVocabularyLessonContents() {
		return vocabularyLessonContents;
	}

	public void setVocabularyLessonContents(List<VocabularyLessonContent> vocabularyLessonContents) {
		this.vocabularyLessonContents = vocabularyLessonContents;
	}

}
