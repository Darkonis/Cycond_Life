package edu.se309.app.backend.rest.entity;

import javax.persistence.*;

/**
 * Class representing monsters
 */
@Entity
@Table(name = "monsters")
public class Monster {

    /**
     * Monster Id primary way of identifying what monster is what.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Currently not being used but will determine what the monster actually is.
     */
    @Column(name = "type")
    private int type;

    /**
     * The Longitude of the given monster
     */
    @Column(name = "longitude")
    private double longitude;

    /**
     * The Latitude of the given monster
     */
    @Column(name = "latitude")
    private double latitude;

    @Column(name = "in_combat")
    private int inCombat;

    public Monster() {
    }

    /**
     * Returns the monster's id
     *
     * @return the monster's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a monster's id to a new id.
     *
     * @param newId The new id for the given monster.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the monster's latitude.
     *
     * @return The monster's latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the longitude for the monster
     *
     * @return The longitude for the monster
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the monster's latitude to a new latitude
     *
     * @param newLat The new latitude for a given monster.
     */

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Returns the type of monster the current monster is.
     *
     * @return The type of monster the current monster is.
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Get combat status of monster
     *
     * @return combat status
     */
    public int getInCombat() {
        return inCombat;
    }

    /**
     * Set combat status of monster
     *
     * @param inCombat combat status
     */
    public void setInCombat(int inCombat) {
        this.inCombat = inCombat;
    }

    /**
     * String representation of Monster
     *
     * @return string representation of Monster
     */
    @Override
    public String toString() {
        return "Monster [id=" + id + ", type=" + type + ", longitude=" + longitude + ", latitude=" + latitude + "]";
    }
}
