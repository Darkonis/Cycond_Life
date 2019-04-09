package com.example.cycondlife;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    Dice d1 = new Dice(3,3,3);
    Dice d2 = new Dice("1+2d4");
    Dice d3 = new Dice ("-3+1d3");
    Dice d4 = new Dice ("-4+-5d-2");
    @Test
    public void roll() {
        assertEquals(d4.base,-4);
        assertEquals(d4.number,-5);
        assertEquals(d4.sides,-2);
        for(int i=0;i<1000;i++) //Test the range for the first input method
        {
            int t = d1.roll();
            assertEquals(t>=6&&t<=12,true);
            assertNotEquals(true,t<6||t>12);
        }
        for(int i=0;i<1000;i++) // Test the range for the second input method
        {
            int t = d2.roll();
            assertEquals(t>=3&&t<=9,true);
            assertNotEquals(true,t<3||t>9);
        }
        for(int i=0;i<1000;i++) // Test the range for the second input method
        {
            int t = d3.roll();
            assertEquals(t>=-2&&t<=0,true);
            assertNotEquals(true,t<-2||t>0);
        }

    }
}