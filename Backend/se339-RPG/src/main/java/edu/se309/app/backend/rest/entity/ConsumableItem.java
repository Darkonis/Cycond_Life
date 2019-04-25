package edu.se309.app.backend.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class used to represent Consumable items
 */
@Entity
@Table(name = "consumable_item")
public class ConsumableItem {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "stat_effected")
    private int statEffected;

    @Column(name = "total_turns")
    private int totalTurns;

    @Column(name = "points_increased")
    private String pointsIncreased;

    /**
     * Get Id
     *
     * @return ID of consumable item
     */
    public int getId() {
        return id;
    }

    /**
     * Set id
     * @param id id of consumable item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Id
     * @return ID of consumable item
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name name of consumable item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get stats effected
     * @return stats effected by consumable item
     */
    public int getStatEffected() {
        return statEffected;
    }

    /**
     * Set stat effected
     * @param statEffected stats effected by consumable item
     */
    public void setStatEffected(int statEffected) {
        this.statEffected = statEffected;
    }

    /**
     * Get total turns
     * @return total turns of consumable item
     */
    public int getTotalTurns() {
        return totalTurns;
    }

    /**
     * Set total turns
     * @param totalTurns total turns of consumable item
     */
    public void setTotalTurns(int totalTurns) {
        this.totalTurns = totalTurns;
    }

    /**
     * Get points increase
     * @return points increased by consumable item
     */
    public String getPointsIncreased() {
        return pointsIncreased;
    }

    /**
     * Set points increased
     * @param pointsIncreased points increased by consumable item
     */
    public void setPointsIncreased(String pointsIncreased) {
        this.pointsIncreased = pointsIncreased;
    }

    /**
     * String representation of consumable item
     * @return string representation of consumable item
     */
    @Override
    public String toString() {
        return "ConsumableItem [Id=" + id + ", name=" + name + ", statEffected="
                + statEffected + ", totalTurns=" + totalTurns +
                ", pointsIncreased=" + pointsIncreased + "]";
    }
}
