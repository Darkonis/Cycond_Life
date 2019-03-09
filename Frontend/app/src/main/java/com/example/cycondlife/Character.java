package com.example.cycondlife;

public class Character {
    /*
    basic stats and info
     */
    private boolean isPlayer;
    private double lat;
    private double lng;
    protected String name = "tmp";
    //potentially move to an enum array
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
    protected int max_resolve;
    /*
    ones unmodded resolve
     */
    protected int base_resolve;
    /*
        The ability to create new things
     */
    protected int tinkering=0;

    protected int critical_thinking =0;
    protected  int base_critical_thinking;
    protected int presentation=0;
    protected  int base_presentation;
    protected String major ="Student";
    protected int tinkering_points;
    protected int base_tinkering;
    protected int max_tinkering;
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
        dice dmg_rng = new dice("1+1d4");
        mon.take_dmg(play.BS+dmg_rng.roll());
        if(mon.resolve <=0) return 1;
        play.take_dmg(mon.BS+dmg_rng.roll());
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
