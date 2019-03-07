package edu.se309.app.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name = "monster_attack")
public class MonsterAttack 
{
	@Id
	@Column(name = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer id;
	
	@Column(name ="name")
	@NotFound(action = NotFoundAction.IGNORE)
	private String name;
	
	@Column(name = "damage")
	@NotFound(action = NotFoundAction.IGNORE)
	private String damage;
	
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDamage()
	{
		return damage;
	}
	public void setDamage(String damage)
	{
		this.damage = damage;
	}
}
