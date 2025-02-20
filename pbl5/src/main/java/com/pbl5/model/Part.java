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
@Table(name = "part")
public class Part {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "part_name", nullable = false, length = 50)
	private String partName;

	@OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reading> readings;

	@OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Listening> listenings;

	public Part() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Part(Long id, String partName, List<Reading> readings, List<Listening> listenings) {
		super();
		this.id = id;
		this.partName = partName;
		this.readings = readings;
		this.listenings = listenings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public List<Reading> getReadings() {
		return readings;
	}

	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}

	public List<Listening> getListenings() {
		return listenings;
	}

	public void setListenings(List<Listening> listenings) {
		this.listenings = listenings;
	}

}
