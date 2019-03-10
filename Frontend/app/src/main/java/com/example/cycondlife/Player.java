package com.example.cycondlife;

import android.content.Context;

/*
    This should be a singleton there should only be one player
 */
public class Player extends Character {
    private static Player player_instance;
    private String username;
    private String password;
    private int id;
    private Player()
    {
        super();
    }
    private final String statlink="/api/stats/updateStat/";
    private Player(String user,int id)
    {
        super();
        username=user;
        name=user;
        this.id=id;
    }
    public static Player get_instance()
    {
        return player_instance;
    }
    public static synchronized void create_the_instance(String user,int id)
    {
        if(player_instance!=null)
        {
            return;
        }
        player_instance = new Player(user,id);
    }
    public static synchronized void destroy_the_instance()
    {
        player_instance=null;
    }
    public void take_dmg(int dmg,Context c)
    {
       resolve=this.resolve-dmg;
        Json_handler j = new Json_handler(c);
        j.update_stat(Player.get_instance().id,"resolve",resolve);
    }
    /*
    TODO adjust this to update locally as well
     */
    protected void update_stat(int val, String stat, Context c)
    {
        Json_handler j = new Json_handler(c);
        j.update_stat(id,stat,val);
    }

}
