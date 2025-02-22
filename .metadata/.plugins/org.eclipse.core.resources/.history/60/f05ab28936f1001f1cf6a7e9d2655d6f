package com.pbl5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grammar")
public class Grammar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "grammar_name", nullable = false, length = 255)
	private String grammarName;

	@Column(name = "image_url", length = 255)
	private String imageUrl;

	@Column(name = "content_html", columnDefinition = "TEXT")
	private String contentHtml;

	@Column(name = "content_mark_down", columnDefinition = "TEXT")
	private String contentMarkDown;

	public Grammar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grammar(Long id, String grammarName, String imageUrl, String contentHtml, String contentMarkDown) {
		super();
		this.id = id;
		this.grammarName = grammarName;
		this.imageUrl = imageUrl;
		this.contentHtml = contentHtml;
		this.contentMarkDown = contentMarkDown;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrammarName() {
		return grammarName;
	}

	public void setGrammarName(String grammarName) {
		this.grammarName = grammarName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	public String getContentMarkDown() {
		return contentMarkDown;
	}

	public void setContentMarkDown(String contentMarkDown) {
		this.contentMarkDown = contentMarkDown;
	}

}
