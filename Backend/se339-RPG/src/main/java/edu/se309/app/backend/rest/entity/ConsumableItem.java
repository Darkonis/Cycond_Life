package edu.se309.app.backend.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consumable_item")
public class ConsumableItem {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "stat_effected")
    private String statEffected;

    @Column(name = "total_turns")
    private int totalTurns;

    @Column(name = "points_increased")
    private int pointsIncreased;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatEffected() {
        return statEffected;
    }

    public void setStatEffected(String statEffected) {
        this.statEffected = statEffected;
    }

    public int getTotalTurns() {
        return totalTurns;
    }

    public void setTotalTurns(int totalTurns) {
        this.totalTurns = totalTurns;
    }

    public int getPointsIncreased() {
        return pointsIncreased;
    }

    public void setPointsIncreased(int pointsIncreased) {
        this.pointsIncreased = pointsIncreased;
    }

    @Override
    public String toString() {
        return "ConsumableItem [Id=" + id + ", name=" + name + ", statEffected="
                + statEffected + ", totalTurns=" + totalTurns +
                ", pointsIncreased=" + pointsIncreased + "]";
    }
}
