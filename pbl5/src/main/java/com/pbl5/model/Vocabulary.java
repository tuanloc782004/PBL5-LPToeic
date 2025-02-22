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
@Table(name = "vocabulary")
public class Vocabulary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "vocabulary_name", nullable = false, length = 255)
	private String vocabularyName;

	@Column(name = "image_url", length = 255, columnDefinition = "VARCHAR(255) DEFAULT '/upload-dir/image/default-vocabulary.jpeg'")
	private String imageUrl;

	@OneToMany(mappedBy = "vocabulary", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VocabularyContent> vocabularyContents;

	public Vocabulary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vocabulary(Long id, String vocabularyName, String imageUrl, List<VocabularyContent> vocabularyContents) {
		super();
		this.id = id;
		this.vocabularyName = vocabularyName;
		this.imageUrl = imageUrl;
		this.vocabularyContents = vocabularyContents;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVocabularyName() {
		return vocabularyName;
	}

	public void setVocabularyName(String vocabularyName) {
		this.vocabularyName = vocabularyName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<VocabularyContent> getVocabularyContents() {
		return vocabularyContents;
	}

	public void setVocabularyContents(List<VocabularyContent> vocabularyContents) {
		this.vocabularyContents = vocabularyContents;
	}

}
