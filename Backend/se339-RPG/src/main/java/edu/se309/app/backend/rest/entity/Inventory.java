package edu.se309.app.backend.rest.entity;

import javax.persistence.*;

/**
 * Class to represent inventory
 */
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "player_id")
    private int playerId;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "item_name")
    private String itemName;

    /**
     * Get ID
     *
     * @return ID of the inventory
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id ID of the inventory
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get player ID
     *
     * @return player ID of the inventory
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Set Player ID
     *
     * @param playerId player ID of the inventory
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Get item ID
     *
     * @return item ID of the inventory
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Set item ID
     *
     * @param itemId item ID of the inventory
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Get item name
     *
     * @return item name of the inventory
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Set item name
     *
     * @param itemName item name of the inventory
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * String representation of inventory
     *
     * @return string representation of inventory
     */
    @Override
    public String toString() {
        return "Inventory [id=" + id + ", playerId=" + playerId + ", itemId=" + itemId
                + ", itemName=" + itemName + "]";
    }
}
