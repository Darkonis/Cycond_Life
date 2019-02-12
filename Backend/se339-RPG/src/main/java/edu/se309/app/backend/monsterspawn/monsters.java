package edu.se309.app.backend.monsterspawn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "monsters")
public class monsters 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;
	
	@Column(name = "type")
	@NotFound(action = NotFoundAction.IGNORE)
	private String type;
	
	@Column(name = "lon")
	@NotFound(action = NotFoundAction.IGNORE)
	private double lon;

	@Column(name = "lat")
	@NotFound(action = NotFoundAction.IGNORE)
	private double lat;
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer newId)
	{
		id = newId;
	}
	
	public String getType()
	{
		return type;
	}
	public void setType(String newType)
	{
		type = newType;
	}
	public double getLon()
	{
		return lon;
	}
	public void setLon(Double newLon)
	{
		lon = newLon;
	}
	public double getLat()
	{
		return lat;
	}
	public void setLat(Double newLat)
	{
		lat = newLat;
	}
	
}
