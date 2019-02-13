package edu.se309.app.backend.monsterspawn;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name = "monsters")
public class Monsters 
{
	/**
	 * Monster Id primary way of identifying what monster is what.
	 */
	@Id
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;
	
	/**
	 * Currently not being used but will determine what the monster actually is.
	 */
	@Column(name = "type")
	@NotFound(action = NotFoundAction.IGNORE)
	private String type;
	
	/**
	 * The Longitude of the given monster
	 */
	@Column(name = "lon")
	@NotFound(action = NotFoundAction.IGNORE)
	private double lon;
	/**
	 * The Latitude of the given monster
	 */
	@Column(name = "lat")
	@NotFound(action = NotFoundAction.IGNORE)
	private double lat;
	/**
	 * Returns the monster's id
	 * @return 
	 * 		the monster's id
	 */
	public Integer getId()
	{
		return id;
	}
	/**
	 * Sets a monster's id to a new id.
	 * @param newId
	 * 		The new id for the given monster.
	 */
	public void setId(Integer newId)
	{
		id = newId;
	}
	/**
	 * Returns the type of monster the current monster is.
	 * @return
	 * 		The type of monster the current monster is.
	 */
	public String getType()
	{
		return type;
	}
	/**
	 * Sets a monster's type to a new type
	 * @param newType
	 * 		The new type for the given monster
	 */
	public void setType(String newType)
	{
		type = newType;
	}
	/**
	 * Returns the longitude for the monster
	 * @return
	 * 		The longitude for the monster
	 */
	public double getLon()
	{
		return lon;
	}
	/**
	 * Sets the monster's longitude to a new longitude
	 * @param newLon
	 * 		The new longitude for the given monster
	 */
	public void setLon(Double newLon)
	{
		lon = newLon;
	}
	/**
	 * Returns the monster's latitude.
	 * @return
	 * 		The monster's latitude.
	 */
	public double getLat()
	{
		return lat;
	}
	/**
	 * Sets the monster's latitude to a new latitude
	 * @param newLat
	 * 		The new latitude for a given monster.
	 */
	public void setLat(Double newLat)
	{
		lat = newLat;
	}
	
}