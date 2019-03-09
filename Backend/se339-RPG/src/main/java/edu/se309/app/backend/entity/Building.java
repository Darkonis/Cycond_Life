package edu.se309.app.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.geo.Polygon;

@Entity
@Table(name = "building_locations")
public class Building {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "building_id")
  private int buildingId;

  @Column(name = "geo", columnDefinition = "geometry")
  private Polygon geo;

  @Column(name = "earned_stat")
  private String earnedStat;

  public Building() {
  }

  public Building(Polygon geo, String earnedStat) {
    this.geo = geo;
    this.earnedStat = earnedStat;
  }

  public int getBuildingId() {
    return buildingId;
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

  public void setEarnedStat(String earnedStat) {
    this.earnedStat = earnedStat;
  }

  public void setGeo(Polygon geo) {
    this.geo = geo;
  }
}