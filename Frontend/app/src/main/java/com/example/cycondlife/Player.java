package com.example.cycondlife;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
    private static int monstersKilled;
    private final String statlink="/api/stats/updateStat/";
    private Player(String user,int id,Context c)
    {
        super();
        username=user;
        name=user;
        this.id=id;
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
      //  RequestQueue q = new Volley.newRequestQueue(c);
       // JsonObjectRequest j = new JsonObjectRequest()
    }
    public static Player get_instance()
    {
        return player_instance;
    }
    public static int getMonstersKilled(){return monstersKilled;}
    public static synchronized void create_the_instance(String user,int id,Context c)
    {
        if(player_instance!=null)
        {
            return;
        }
        player_instance = new Player(user,id,c);
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
