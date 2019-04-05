package com.example.cycondlife;

public class Consumable extends Item {

    private final int health = 0x0;
   private Dice effect;
   private int duration;
   private String useMsg;

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

    public void use()
    {
        switch (type)
        {
            case health:
            Player.get_instance().changeResolve(effect.roll());
            break;
        }
    }
    public String getUseMsg() {
        return useMsg;
    }
    public int getDuration() {
        return duration;
    }
    public Dice getEffect() {
        return effect;
    }
}
