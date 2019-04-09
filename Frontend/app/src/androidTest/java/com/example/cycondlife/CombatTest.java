package com.example.cycondlife;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CombatTest {

    @Test
    public void set_combatants() {
        Character c = new Character();
        Player.createTestInstance("s",0);
        Player p = Player.get_instance();
        Game g = new Game(null);
        Combat.set_combatants(c,g);
        assertEquals(p,Combat.player);
        assertEquals(c,Combat.monster);
    }

}