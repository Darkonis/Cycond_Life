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
    private int statEffected;

    @Column(name = "total_turns")
    private int totalTurns;

    @Column(name = "points_increased")
    private String pointsIncreased;

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

    public int getStatEffected() {
        return statEffected;
    }

    public void setStatEffected(int statEffected) {
        this.statEffected = statEffected;
    }

    public int getTotalTurns() {
        return totalTurns;
    }

    public void setTotalTurns(int totalTurns) {
        this.totalTurns = totalTurns;
    }

    public String getPointsIncreased() {
        return pointsIncreased;
    }

    public void setPointsIncreased(String pointsIncreased) {
        this.pointsIncreased = pointsIncreased;
    }

    @Override
    public String toString() {
        return "ConsumableItem [Id=" + id + ", name=" + name + ", statEffected="
                + statEffected + ", totalTurns=" + totalTurns +
                ", pointsIncreased=" + pointsIncreased + "]";
    }
}
