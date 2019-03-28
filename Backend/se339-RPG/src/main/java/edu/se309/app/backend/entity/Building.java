package edu.se309.app.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "building_locations")
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

//	@Column(name = "geo", columnDefinition = "GEOMETRY")	
//	private Geometry geo;

	@Column(name = "earned_stat")
	private String earnedStat;

	@Column(name = "building_name")
	private String buildingName;

	public Building() {
	}	

	public int getId() {
		return id;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public String getEarnedStat() {
		return earnedStat;
	}

//	public Geometry getGeo() {
//		return geo;
//	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public void setEarnedStat(String earnedStat) {
		this.earnedStat = earnedStat;
	}

//	public void setGeo(Geometry geo) {
//		this.geo = geo;
//	}
}