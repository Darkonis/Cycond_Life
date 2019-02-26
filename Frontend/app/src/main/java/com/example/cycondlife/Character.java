package com.example.cycondlife;

public class Character {
    /*
    basic stats and info
     */
    private boolean isPlayer;
    private double lat;
    private double lng;
    private String name;
    //potentially move to an enum array
    /*
    the ability to quickly and rapidly confuse your opponent (attack)
     */
    private int BS;
    /*
        ones determination in combat basically hp
     */
    private int resolve;
    /*
    The stat of Tinkers Enginneers use their abilitys to create and design objects for both innovation and support (basically casters)
    The difficulty of their field makes them very succeptible to BS
     */
    private int engineering;
    /*
    the stat of the tanks science and math majors use their skills to unravel the secerts of the universe and thus are unconcerned with bs
     */
    private int sci_and_math;
    /*
    primary Stat of dmg as they create well worded arguements to cut through bs
     */
    private int liberal_arts;

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
}
