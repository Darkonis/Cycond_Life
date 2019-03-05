package edu.se309.app.backend.entity;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "monster_stat")
public class MonsterStat 
{
	@Id
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;
	
	@Column(name = "name")
	@NotFound(action = NotFoundAction.IGNORE)
	private String name;
	
	@Column(name = "hp")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer hp;
	
	@Column(name = "attackOne")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackOne;
	
	@Column(name = "attackTwo")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackTwo;
	
	@Column(name = "attackThree")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackThree;
	
	@Column(name = "attackFour")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackFour;
	
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
	public Integer getHp()
	{
		return hp;
	}
	public void setHp(Integer hp)
	{
		this.hp = hp;
	}
	public Integer getAttackOne()
	{
		return attackOne;
	}
	public void setAttackOne(Integer attackOne)
	{
		this.attackOne = attackOne;
	}
	public Integer getAttackTwo()
	{
		return attackTwo;
	}
	public void setAttackTwo(Integer attackTwo)
	{
		this.attackTwo = attackTwo;
	}
	public Integer getAttackThree()
	{
		return attackThree;
	}
	public void setAttackThree(Integer attackThree)
	{
		this.attackThree = attackThree;
	}
	public Integer getAttackFour()
	{
		return attackFour;
	}
	public void setAttackFour(Integer attackFour)
	{
		this.attackFour = attackFour;
	}
	public Integer getRandomAttack()
	{
		Random rand = new Random(0);
		int attack = Math.abs(rand.nextInt()%4+1);
		return attack;
	}
}
