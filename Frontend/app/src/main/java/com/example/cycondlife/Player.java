package com.example.cycondlife;

/*
    This should be a singleton there should only be one player
 */
public class Player extends Character {
    private static Player player_instance;
    private String username;
    private String password;
    private Player()
    {
        super();
    }
    private Player(String user)
    {
        super();
        username=user;
    }
    public static Player get_instance()
    {
        return player_instance;
    }
    public static synchronized void create_the_instance(String user)
    {
        if(player_instance!=null)
        {
            return;
        }
        player_instance = new Player(user);
    }
    public static synchronized void destroy_the_instance()
    {
        player_instance=null;
    }

}
