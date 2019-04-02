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
}
