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
	
	@Column(name = "attack_one")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackOne;
	
	@Column(name = "attack_two")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackTwo;
	
	@Column(name = "attack_three")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackThree;
	
	@Column(name = "attack_four")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackFour;
	
	@Column(name = "attack_percent_one")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackPercentOne;
	
	@Column(name = "attack_percent_two")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackPercentTwo;
	
	@Column(name = "attack_percent_three")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackPercentThree;
	
	@Column(name = "attack_percent_four")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer attackPercentFour;
	
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
	public Integer getAttackPercentOne()
	{
		return attackPercentOne;
	}
	public void setAttackPercentOne(Integer attackPercentOne)
	{
		this.attackPercentOne = attackPercentOne;
	}
	public Integer getAttackPercentTwo()
	{
		return attackPercentTwo;
	}
	public void setAttackPercentTwo(Integer attackPercentTwo)
	{
		this.attackPercentTwo = attackPercentTwo;
	}
	public Integer getAttackPercentThree()
	{
		return attackPercentThree;
	}
	public void setAttackPercentThree(Integer attackPercentThree)
	{
		this.attackPercentThree = attackPercentThree;
	}
	public Integer getAttackPercentFour()
	{
		return attackPercentFour;
	}
	public void setAttackPercentFour(Integer attackPercentFour)
	{
		this.attackPercentFour = attackPercentFour;
	}
	public Integer getRandomAttack()
	{
		Random rand = new Random(0);
		int attack = Math.abs(rand.nextInt()%100);
		int firstBarrier = this.getAttackPercentOne();
		int secondBarrier = firstBarrier + this.getAttackPercentTwo();
		int thirdBarrier = secondBarrier + this.getAttackPercentThree();
		int fourthBarrier = thirdBarrier + this.getAttackPercentFour();
		if(attack < firstBarrier)
		{
			return this.getAttackOne();
		}
		else if(attack < secondBarrier)
		{
			return this.getAttackTwo();
		}
		else if(attack < thirdBarrier)
		{
			return this.getAttackThree();
		}
		else if(attack < fourthBarrier)
		{
			return this.getAttackFour();
		}
		return 0;
	}
}
