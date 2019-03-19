package com.example.cycondlife;

import java.util.Random;

public class Dice {
    int sides=0;
    int base=0;
    int number=0;
    Random rand;
    public Dice(String s)
    {
        //CharSequence c = "d";
        base = Integer.parseInt(s.substring(0,s.indexOf('+')));
        number = Integer.parseInt(s.substring(s.indexOf('+'),s.indexOf('d')));
        sides= Integer.parseInt(s.substring(s.indexOf('d')+1));
        /*
        this might not work
         */
         rand = new Random(System.currentTimeMillis());
    }
    public Dice(int base, int numb, int sides)
    {
        this.sides=sides;
        this.base=base;
        this.number=numb;
        rand = new Random(System.currentTimeMillis());
    }
    public void setSeed(int seed)
    {
        rand = new Random(seed);
    }
    public int roll()
    {
        if(sides==0 && base==0 && number==0)return -1;
        int sum=0;
        for(int i=0;i<number;i++)
        {
            sum+=rand.nextInt(sides)+1;
        }
        return base+sum;
    }
}
