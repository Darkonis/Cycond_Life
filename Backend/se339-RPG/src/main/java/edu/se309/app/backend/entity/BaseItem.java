package edu.se309.app.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "base_item")
public class BaseItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private int type;
	
	@Column(name = "type_id")
	private int typeId;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getType() {
		return type;
	}
	
	public int getTypeId() {
		return typeId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	
	public void setTypeId(int typeId)
	{
		this.typeId = typeId;
	}
	
	@Override
	public String toString() {
		return "BaseItem [id=" + id + ", name=" + name + ", type=" + type + ", typeId=" + typeId +"]";
	}
}
