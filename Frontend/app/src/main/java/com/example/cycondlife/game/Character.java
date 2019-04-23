package com.example.cycondlife.game;

public class Character {
    /*
    basic stats and info
     */
    private boolean isPlayer;
    private double lat;
    private double lng;
    protected int presentation=10;
    protected int creativity=10;
    protected int critical_thinking=10;
    protected String major="student";
    protected String name = "tmp";
    //potentially move to an enum array
    //TODO change to formula calculation
    /*
    the ability to quickly and rapidly confuse your opponent (attack)
     */
    protected int BS=10;
    /*
        ones determination in combat basically hp
     */
    protected int resolve=100;
    /*
    ones potential for resolve
     */


   public Character()
    {

        isPlayer=false;
        lat =42.03;
        lng =-93.63;
        BS =10;
        resolve =100;
    }
    /*
    creates a generic monster for testing
     */
    public Character(int id, int type, double lat,double lng)
    {
        isPlayer=false;
        BS=10;
        resolve=100;
        this.lat=lat;
        this.lng=lng;
        name = "Monster:"+id;
    }
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
    also remove character play its not signleton style
     */

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
   // public int getBS(){return BS;}
    public String getMajor(){return major;}
    public int getPresentation(){return presentation;}

}