package edu.se309.app.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consumable_item")
public class ConsumableItem {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "stat_effected")
	private String statEffected;
	
	@Column(name = "total_turns")
	private int totalTurns;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getStatEffected() {
		return statEffected;
	}
	
	public int getTotalTurns() {
		return totalTurns;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStatEffected(String statEffected) {
		this.statEffected = statEffected;
	}
	
	public void setTotalTurns(int totalTurns) {
		this.totalTurns = totalTurns;
	}
	
	@Override
	public String toString() {
		return "ConsumableItem [Id=" + id +", name=" + name + ", statEffected=" 
				+ statEffected +", totalTurns=" + totalTurns + "]";
	}
}
