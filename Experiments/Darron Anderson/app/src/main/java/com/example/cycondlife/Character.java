package com.example.cycondlife;

public class Character {
    /*
    basic stats and info
     */
    private boolean isPlayer;
    private double lat;
    private double lng;
    //potentially move to an enum array
    private int atk;
    private int def;
    private int hp;
    private int spd;
    private int inte;
    private int chr;
    //the distance monsters will appear
    private double visual_range=.5;
    Character()
    {

        isPlayer=false;
        lat =42.03;
        lng =93.63;
        atk =10;
        def =10;
        hp =10;
        spd =10;
        inte =10;
        chr =10;
    }
    Character(boolean isPlayer,double lat,double longitude,int attack,int defense,int hp,int spd,int inte,int chr)
    {
        this.isPlayer=isPlayer;
        this.lat = lat;
        lng =longitude;
        atk =attack;
        def =defense;
        this.hp =hp;
        this.spd =spd;
        this.inte =inte;
        this.chr =chr;
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
}
