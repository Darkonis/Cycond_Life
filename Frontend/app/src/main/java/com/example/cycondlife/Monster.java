package com.example.cycondlife;

import org.json.JSONObject;

import java.util.Random;

public class Monster extends Character {
    Dice attack1;
    Dice attack2;
    Dice attack3;
    Dice attack4;
    int percent1;
    int percent2;
    int percent3;
    int percent4;
    Monster(int id, int type, double lat,double lng,Dice a1,Dice a2,Dice a3,Dice a4,int p1,int p2,int p3, int p4)
    {
        super(id,type,lat,lng);
        attack1 =a1;
        attack2=a2;
        attack3=a3;
        attack4=a4;
        percent1=p1;
        percent2=p2;
        percent3=p3;
        percent4=p4;
    }
    public int make_an_attack( Random rand)
    {
        int atk=rand.nextInt()%100;
        if(atk<percent1)return attack1.roll();
        if(atk<percent1+percent2)return attack2.roll();
        if(atk<percent1+percent2+percent3)return attack3.roll();
        return attack4.roll();
    }

}
