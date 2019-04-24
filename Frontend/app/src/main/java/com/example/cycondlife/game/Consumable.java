package com.example.cycondlife.game;

import com.example.cycondlife.Dice;

public class Consumable extends Item {

    //TODO make an enum
    public static final int  health = 0x0;
    public static final int tinkering = 0x1;
    public static final int  creativity = 0x2;
    public static final int presentation= 0x3;
    public static final int  criticalThinking = 0x4;
    public static final int special= 0x5;
    private Dice effect;

    private int duration;
   private String useMsg;

    /**
     * Make a consumable item
     * @param itemID
     * @param name the items name
     * @param desc the description of the item
     * @param type what type of item is it
     * @param effect dice type that controls the power of the effect
     * @param duration how long effects last (in turns)
     * @param use_msg what should be told to the player when an item is used
     */
    Consumable(int itemID,String name,String desc,int type,Dice effect,int duration,String use_msg)
    {
        this.itemID=itemID;
        this.name=name;
        description=desc;
        this.type=type;
        this.effect=effect;
        this.duration=duration;
        this.useMsg=use_msg;
    }

    /**
     * use and item
     * @return weither t
     */
    public boolean use()
    {
        boolean used = true;
        switch (type)
        {
            case health:
            Player.get_instance().changeResolve(effect.roll());
            break;
            case tinkering:
                Player.get_instance().adjustTinkeringPoints(effect.roll());
                break;
            case criticalThinking:
            case creativity:
            case presentation:
                Player.get_instance().addActiveItem(this.clone());
                break;
            default:
                used=false;
        }
        return used;
    }

    /**
     * Decrease the remaining duration of an item
     */
    public void decreaseDuration() {duration--;}
    public String getUseMsg() {
        return useMsg;
    }

    /**
     * get the duration
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * return the effect of an item
     * @return dice with effect
     */
    public Dice getEffect() {
        return effect;
    }

    /**
     * create a copy
     * @return the copy
     */
    @Override
    public Consumable clone()
    {
        return new Consumable(itemID,name,description,type,effect,duration,useMsg);
    }

}
