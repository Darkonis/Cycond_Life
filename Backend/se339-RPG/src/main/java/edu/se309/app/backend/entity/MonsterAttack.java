package edu.se309.app.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "monster_attack")
public class MonsterAttack {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "damage")
  private String damage;

  public MonsterAttack() {
  }

  public String getDamage() {
    return damage;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setDamage(String damage) {
    this.damage = damage;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "MonsterAttack [id=" + id + ", name=" + name + ", damage=" + damage + "]";
  }
}
