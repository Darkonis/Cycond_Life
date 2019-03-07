package edu.se309.app.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name = "monsters")
public class Monster 
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
	private Integer type;
	
	/**
	 * The Longitude of the given monster
	 */
	@Column(name = "longitude")
	@NotFound(action = NotFoundAction.IGNORE)
	private double longitude;
	/**
	 * The Latitude of the given monster
	 *
	 */
	@Column(name = "latitude")
	@NotFound(action = NotFoundAction.IGNORE)
	private double latitude;
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
	 * Returns the monster's latitude.
	 * @return
	 * 		The monster's latitude.
	 */
	public double getLat()
	{
		return latitude;
	}
	/**
	 * Returns the longitude for the monster
	 * @return
	 * 		The longitude for the monster
	 */
	public double getLon()
	{
		return longitude;
	}
	/**
	 * Returns the type of monster the current monster is.
	 * @return
	 * 		The type of monster the current monster is.
	 */
	public Integer getType()
	{
		return type;
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
	 * Sets the monster's latitude to a new latitude
	 * @param newLat
	 * 		The new latitude for a given monster.
	 */
	public void setLat(Double newLat)
	{
		latitude = newLat;
	}
	/**
	 * Sets the monster's longitude to a new longitude
	 * @param newLon
	 * 		The new longitude for the given monster
	 */
	public void setLon(Double newLon)
	{
		longitude = newLon;
	}
	/**
	 * Sets a monster's type to a new type
	 * @param newType
	 * 		The new type for the given monster
	 */
	public void setType(Integer newType)
	{
		type = newType;
	}
	
}