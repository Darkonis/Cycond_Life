package com.example.cycondlife;

import com.example.cycondlife.game.Character;
import com.example.cycondlife.game.Combat;
import com.example.cycondlife.game.Game;
import com.example.cycondlife.game.Player;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CombatTest {

    @Test
    public void set_combatants() {
        Character c = new Character();
        Player.createTestInstance("s",0);
        Player p = Player.get_instance();
        Game g = new Game(null);
        Combat.set_combatants(c,g);
        assertEquals(p, Combat.player);
        assertEquals(c, Combat.monster);
    }

}