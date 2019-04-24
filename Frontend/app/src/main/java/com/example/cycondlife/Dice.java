package com.example.cycondlife;

import java.util.Random;

public class Dice {
    private int sides=0;
    private int base=0;
    private int number=0;
    private Random rand = new Random(System.currentTimeMillis());

    /**
     * create a dice by parsing a string
     * @param s String to be parsed in the form of X+YdZ where X = base value Y is number of times the dice will be rolled when it is rolled and Z being the sides on the die
     */
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

    /**
     * create a dice by passing the parameters
     * @param base the base amount of the dice
     * @param numb the number of times to roll the dice
     * @param sides number of sides on the dice
     */
    public Dice(int base, int numb, int sides)
    {
        this.sides=sides;
        this.base=base;
        this.number=numb;

    }

    /**
     * force the seed of the random just in case we need to test for specific random events
     * @param seed
     */
    public void setSeed(int seed)
    {
        rand = new Random(seed);
    }

    /**
     * rolls the dice
     * @return the amount rolled
     */
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
