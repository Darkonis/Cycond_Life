package com.example.cycondlife;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ConsumableTest {

    Player p;
    ArrayList<Item> i = new ArrayList<>();
    Consumable t = new Consumable(001,"Test Item","This shouldn't be seen",0,new Dice("3+3d8"),0,"The item was used");
    Consumable t2 = new Consumable(-1,"Test failure Item","breaking stuff",-1,new Dice("3+3d8"),3,"The item broke");

    @Before
    public void setUp() throws Exception {
        Player.createTestInstance("Test",01);
        p= Player.get_instance();
        p.addItem(t);
        p.addItem(t2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void use() {
            i.add(t);
        i.add(t2);
        assertEquals(((Consumable) i.get(0)).use(),true);
        assertEquals(((Consumable) i.get(1)).use(),false);
        assertFalse(p.getInv().contains(t)); //This will fail until items are consumed by use

    }

    @Test
    public void getUseMsg() {
        assertEquals(true,"The item was used".equals(t.getUseMsg()));
    }

    @Test
    public void getDuration() {
    assertEquals(0,t.getDuration());
    assertEquals(3,t2.getDuration());
    }
}