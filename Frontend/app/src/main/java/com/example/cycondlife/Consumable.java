package com.example.cycondlife;

public class Consumable extends Item {

    private final int  health = 0x0;
    private final int tinkering = 0x1;
    private final int  creativity = 0x2;
    private final int presentation= 0x3;
    private final int  criticalThinking = 0x4;
    private final int special= 0x5;
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
                Player.get_instance().addActiveItem(this);
                break;
            default:
                used=false;
        }
        return used;
    }
    public String getUseMsg() {
        return useMsg;
    }
    public int getDuration() {
        return duration;
    }
    public void decreaseDuration() {duration--;}
    public Dice getEffect() {
        return effect;
    }
}
