package edu.se309.app.backend.rest.entity;

import javax.persistence.*;

/**
 * Class representation of monster attacks
 */
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

    /**
     * Default constructor for monster attack
     */
    public MonsterAttack() {
    }

    /**
     * Get damage
     *
     * @return damage of Monster Attack
     */
    public String getDamage() {
        return damage;
    }

    /**
     * Set damage
     *
     * @param damage damage of Monster Attack
     */
    public void setDamage(String damage) {
        this.damage = damage;
    }

    /**
     * Get id
     *
     * @return id of Monster Attack
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id ID of Monster Attack
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get name
     *
     * @return name of Monster Attack
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     * @param name name of Monster Attack
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * String representation of Monster Attack
     *
     * @return string representation of Monster Attack
     */
    @Override
    public String toString() {
        return "MonsterAttack [id=" + id + ", name=" + name + ", damage=" + damage + "]";
    }
}
