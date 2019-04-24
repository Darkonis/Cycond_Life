package edu.se309.app.backend.rest.entity;

import javax.persistence.*;

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
    
    public int getId() {
    	return id;
    }
    
    public int getPlayerId() {
    	return playerId;
    }
    
    public int getItemId() {
    	return itemId;
    }
    
    public String getItemName() {
    	return itemName;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public void setPlayerId(int playerId) {
    	this.playerId = playerId;
    }
    
    public void setItemId(int itemId) {
    	this.itemId = itemId;
    }
    
    public void setItemName(String itemName) {
    	this.itemName = itemName;
    }
    
    @Override
    public String toString() {
    	return "Inventory [id=" + id + ", playerId=" + playerId + ", itemId=" + itemId 
    			+ ", itemName=" + itemName + "]";
    }
}
