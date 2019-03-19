package com.example.cycondlife;

public abstract class Item {
    protected int itemID;
    protected String name;
    protected String description;
    protected int type;

    public int getType(){
        return type;
    }
    public int getItemID()
    {
        return itemID;
    }

}
