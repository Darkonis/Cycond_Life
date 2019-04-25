package edu.se309.app.backend.rest.entity;

import javax.persistence.*;

/**
 * Class used to represent base item
 */
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

    /**
     * Get ID
     *
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID
     * @param id id of base item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get name
     * @return name of base item
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name name of base item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get type
     * @return type of base item
     */
    public int getType() {
        return type;
    }

    /**
     * Set type
     * @param type type of base item
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Get Type Id
     * @return type id of base item
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Set type id
     * @param typeId type id of base item
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    /**
     * Get value
     * @return value of base item
     */
    public int getValue() {
        return value;
    }

    /**
     * Set value
     * @param value value of base item
     */
    public void setValue(int value) {
        this.value = value;
    }


    /**
     * Returns string representation of base item
     * @return string representation of base item
     */
    @Override
    public String toString() {
        return "BaseItem [id=" + id + ", name=" + name + ", type=" + type + ", typeId=" + typeId + "]";
    }
}
