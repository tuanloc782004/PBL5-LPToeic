package com.pbl5.dto;

public class LessonCountDTO {

	private String label;
	private long count;

	public LessonCountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LessonCountDTO(String label, long count) {
		super();
		this.label = label;
		this.count = count;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
