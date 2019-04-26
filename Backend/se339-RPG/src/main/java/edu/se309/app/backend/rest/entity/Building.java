package edu.se309.app.backend.rest.entity;

import javax.persistence.*;

/**
 * Class representing building locations
 */
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

    /**
     * Default Constructor
     */
    public Building() {
    }

    /**
     * Get ID
     *
     * @return ID of building location
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id ID of building location
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Building Name
     *
     * @return building's name
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Set building's name
     *
     * @param buildingName building's name
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * Get the stat earned by being in the building
     *
     * @return stat earned
     */
    public String getEarnedStat() {
        return earnedStat;
    }

    /**
     * Set stat earned by being in the building
     *
     * @param earnedStat stat earned
     */
    public void setEarnedStat(String earnedStat) {
        this.earnedStat = earnedStat;
    }

}