package edu.se309.app.backend.entity;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "monster_stat")
public class MonsterStat 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")   
    private int id;
	
	@Column(name = "name")	
	private String name;
	
	@Column(name = "hp")	
	private int hp;
	
	@Column(name = "attack_one")	
	private int attackOne;
	
	@Column(name = "attack_two")	
	private int attackTwo;
	
	@Column(name = "attack_three")	
	private int attackThree;
	
	@Column(name = "attack_four")	
	private int attackFour;
	
	@Column(name = "attack_percent_one")	
	private int attackPercentOne;
	
	@Column(name = "attack_percent_two")	
	private int attackPercentTwo;
	
	@Column(name = "attack_percent_three")	
	private int attackPercentThree;
	
	@Column(name = "attack_percent_four")	
	private int attackPercentFour;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
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
	public int getHp()
	{
		return hp;
	}
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	public int getAttackOne()
	{
		return attackOne;
	}
	public void setAttackOne(int attackOne)
	{
		this.attackOne = attackOne;
	}
	public int getAttackTwo()
	{
		return attackTwo;
	}
	public void setAttackTwo(int attackTwo)
	{
		this.attackTwo = attackTwo;
	}
	public int getAttackThree()
	{
		return attackThree;
	}
	public void setAttackThree(int attackThree)
	{
		this.attackThree = attackThree;
	}
	public int getAttackFour()
	{
		return attackFour;
	}
	public void setAttackFour(int attackFour)
	{
		this.attackFour = attackFour;
	}
	public int getAttackPercentOne()
	{
		return attackPercentOne;
	}
	public void setAttackPercentOne(int attackPercentOne)
	{
		this.attackPercentOne = attackPercentOne;
	}
	public int getAttackPercentTwo()
	{
		return attackPercentTwo;
	}
	public void setAttackPercentTwo(int attackPercentTwo)
	{
		this.attackPercentTwo = attackPercentTwo;
	}
	public int getAttackPercentThree()
	{
		return attackPercentThree;
	}
	public void setAttackPercentThree(int attackPercentThree)
	{
		this.attackPercentThree = attackPercentThree;
	}
	public int getAttackPercentFour()
	{
		return attackPercentFour;
	}
	public void setAttackPercentFour(int attackPercentFour)
	{
		this.attackPercentFour = attackPercentFour;
	}
	public int getRandomAttack()
	{
		Random rand = new Random();
		int attack = rand.nextInt(100);
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
