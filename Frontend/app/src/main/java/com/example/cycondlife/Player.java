package com.example.cycondlife;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

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
    private static int monstersKilled;
    private Player(String user)
    {
        super();
        username=user;
        name=user;
        Callback_handler callback = new Callback_handler() {
            @Override
            public void get_response(JSONArray a) {
                return;
            }

            @Override
            public void get_object_response(JSONObject o) {
                try {

                   resolve= o.getInt("resolve");
                   tinkering = o.getInt("tinkering");
                   BS = o.getInt("BS");
                   presentation = o.getInt("presentation");
                   monstersKilled = o.getInt("monstersKilled");

                }
                catch(Exception e)
                {
                    Log.i("Cycond Life","Stat pull error");
                }
            }
        };

    }
    public static Player get_instance()
    {
        return player_instance;
    }
    public static int getMonstersKilled(){return monstersKilled;}
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
