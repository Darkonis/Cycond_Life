package edu.se309.app.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "building_locations")
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "building_id")
	private int buildingId;

	@Column(name = "geo", columnDefinition = "geometry(Polygon,4326)")
	@JsonIgnore
	private Polygon geo;

	@Column(name = "earned_stat")
	private String earnedStat;

	@Column(name = "building_name")
	private String buildingName;

	public Building() {
	}

	public Building(Polygon geo, String earnedStat) {
		this.geo = geo;
		this.earnedStat = earnedStat;
	}

//	public int getBuildingId() {
//		return buildingId;
//	}

	public String getBuildingName() {
		return buildingName;
	}

	public String getEarnedStat() {
		return earnedStat;
	}

	public Polygon getGeo() {
		return geo;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public void setEarnedStat(String earnedStat) {
		this.earnedStat = earnedStat;
	}

	public void setGeo(Polygon geo) {
		this.geo = geo;
	}
}
