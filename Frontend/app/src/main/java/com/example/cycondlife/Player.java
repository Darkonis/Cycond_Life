package com.example.cycondlife;

/*
    This should be a singleton there should only be one player
 */
public class Player extends Character {
    private static final Player player_instance = new Player();
    private Player()
    {
        super();
    }
    public static Player get_instance()
    {
        return player_instance;
    }


}
