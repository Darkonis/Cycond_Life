package com.example.cycondlife;

public class Character {
    /*
    basic stats and info
     */
    private boolean isPlayer;
    private double lat;
    private double lng;
    private String name = "tmp";
    //potentially move to an enum array
    /*
    the ability to quickly and rapidly confuse your opponent (attack)
     */
    private int BS;
    /*
        ones determination in combat basically hp
     */
    private int resolve;
    private int max_resolve;
    private int tinkering;
    private int critical_thinking;
    private int presentation;
    private String major ="Student";
    private int tinkering_points;
    //the distance monsters will appear
    private double visual_range=.5;
    Character()
    {

        isPlayer=false;
        lat =42.03;
        lng =93.63;
        BS =10;
        resolve =100;
    }
    /*
    creates a generic monster for testing
     */
    public Character(double lat,double lng)
    {
        isPlayer=false;
        BS =10;
        resolve=100;
        this.lat=lat;
        this.lng=lng;
        name="tmp";

    }
    Character(boolean isPlayer,double lat,double longitude,int attack)
    {
        this.isPlayer=isPlayer;
        this.lat = lat;
        lng =longitude;
        BS =attack;
    }
    public double get_latitude()
    {
        return lat;
    }
    public double get_longitude()
    {
        return lng;
    }
    public void setLat(double lat)
    {
        this.lat=lat;
    }
    public void setLong(double lng)
    {
        this.lng=lng;
    }
    boolean is_player()
    {
        return isPlayer;
    }
    public void take_dmg(int dmg)
    {
        this.resolve=this.resolve-dmg;
    }
    /*
    maybe move this into combat class
     */
    public static int do_combat(Character play, Character mon)
    {
        mon.take_dmg(play.BS);
        if(mon.resolve <=0) return 1;
        play.take_dmg(mon.BS);
        if(play.resolve<=0) return 2;
        return 0;
    }
    public int getResolve() {return resolve;}
    public void setResolve (int i) {resolve=i;}
    /*
     private int BS
    private int resolve;
    private int max_resolve;
    private int tinkering;
    private int critical_thinking;
    private int presentation;
    private String major ="Student";
     */
    public String getName(){return name;}
    public int getBS(){return BS;}
    public String getMajor(){return major;}
    public int getMax_resolve(){return max_resolve;}
    public int getTinkering(){return tinkering;}
    public int getCritical_thinking(){return critical_thinking;}
    public int getPresentation(){return presentation;}

}
