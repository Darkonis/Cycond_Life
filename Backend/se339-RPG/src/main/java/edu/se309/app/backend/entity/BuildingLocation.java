package edu.se309.app.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.geo.Polygon;

@Entity
@Table(name="stats")
public class BuildingLocation {

	public enum Stats 
    { 
        BS, RESOLVE, CRITICALTHINKING,
        INGENUITY, PRESENTATION, NONE; 
    } 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="building_id")
	private int buildingId;
	
	@Column(name="geo", columnDefinition = "geometry")
	private Polygon geo;
	
	@Column(name="earned_stat")
	private Stats earnedStat;

	public BuildingLocation() {
		
	}
	
	public BuildingLocation(Polygon geo, Stats earnedStat) {
		this.geo = geo;
		this.earnedStat = earnedStat;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public Polygon getGeo() {
		return geo;
	}

	public void setGeo(Polygon geo) {
		this.geo = geo;
	}

	public Stats getEarnedStat() {
		return earnedStat;
	}

	public void setEarnedStat(Stats earnedStat) {
		this.earnedStat = earnedStat;
	}
	
	
	
	
	
}
