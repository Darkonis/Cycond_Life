package com.example.cycondlife;

import java.util.Random;

public class Dice {
    private int sides=0;
    private int base=0;
    private int number=0;
    private Random rand = new Random(System.currentTimeMillis());
    public Dice(String s)
    {
        //CharSequence c = "d";
        base = Integer.parseInt(s.substring(0,s.indexOf('+')));
        number = Integer.parseInt(s.substring(s.indexOf('+'),s.indexOf('d')));
        sides= Integer.parseInt(s.substring(s.indexOf('d')+1));
        /*
        this might not work
         */

    }
    public Dice(int base, int numb, int sides)
    {
        this.sides=sides;
        this.base=base;
        this.number=numb;

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
