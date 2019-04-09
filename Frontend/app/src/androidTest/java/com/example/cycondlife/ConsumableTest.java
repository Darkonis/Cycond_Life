package com.example.cycondlife;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ConsumableTest {

    Player p;
    ArrayList<Item> i = new ArrayList();
    @Before
    public void setUp() throws Exception {
        Player.createTestInstance("Test",01);
        p=Player.get_instance();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void use() {
        i.add(new Consumable(001,"Test Item","This shouldn't be seen",0,new Dice("3+3d8"),0,"The item was used"))

    p.addItem();
        p.addItem();
    }

    @Test
    public void getUseMsg() {
    }

    @Test
    public void getDuration() {
    }

    @Test
    public void getEffect() {
    }
}