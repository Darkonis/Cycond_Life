package edu.se309.app.backend.rest.entity;

import javax.persistence.*;

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
    
    @Column(name = "value")
    private int value;
    
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    
    public int getValue() {
    	return value;
    }
    
    public void setValue(int value) {
    	this.value = value;
    }


    @Override
    public String toString() {
        return "BaseItem [id=" + id + ", name=" + name + ", type=" + type + ", typeId=" + typeId + "]";
    }
}
