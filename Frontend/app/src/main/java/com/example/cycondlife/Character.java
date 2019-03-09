package com.example.cycondlife;

public class Character {
    /*
    basic stats and info
     */
    protected boolean isPlayer;
    protected double lat;
    protected double lng;
    protected String name = "tmp";
    //potentially move to an enum array
    /*
    the ability to quickly and rapidly confuse your opponent (attack)
     */
    protected int BS;
    /*
        ones determination in combat basically hp
     */
    protected int resolve;
    /*
    ones potential for resolve
     */
    protected int max_resolve;
    /*
    ones unmodded resolve
     */
    protected int base_resolve;
    /*
        The ability to create new things
     */
    protected int tinkering;

    protected int critical_thinking;
    protected  int base_critical_thinking;
    protected int presentation;
    protected  int base_presentation;
    protected String major ="Student";
    protected int tinkering_points;
    protected int base_tinkering;
    protected int max_tinkering;
    //the distance monsters will appear
    /*
    TODO might make this variable base on class a trouble sense is you will
     */
    protected double visual_range=.03;
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
