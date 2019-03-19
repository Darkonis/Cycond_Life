package com.example.cycondlife;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import static com.android.volley.toolbox.Volley.newRequestQueue;

/*
    This should be a singleton there should only be one player
 */
public class Player extends Character {
    private static Player player_instance;
    private Player()
    {
        super();
    }
    private static int monstersKilled;
    private final String statlink="/api/stats/updateStat/";
    private Context context;
    private Callback_handler callback;
    private int experiance=0;
    private int level =1;
    private Player(String user,int idt,Context c)
    {
        super();
       // username=user;
        name=user;
        this.id=idt;
        this.context = c;
        callback = new Callback_handler() {
            @Override
            public void get_response(JSONArray a) {
                for (int i = 0; i < a.length(); i++)
                {
                    try {


                        if (a.getJSONObject(i).getInt("accountId")==id)
                        {
                            get_stats(a.getJSONObject(i).getInt("id"),this,context);
                        }
                    }
                    catch (Exception e) {
                        Log.i("Cycond error", "error getting user info");
                        e.printStackTrace();
                    }
                }
            }


            @Override
            public void get_object_response(JSONObject o) {
                try {
                   presentation = o.getInt("presentation");
                   monstersKilled = o.getInt("monstersKilled");
                    critical_thinking = o.getInt("critical thinking");
                    creativity = o.getInt("creativity");
                    BS=presentation+critical_thinking;
                    resolve= presentation;
                }
                catch(Exception e)
                {
                    Log.i("Cycond Life","Stat pull error");
                }
            }
        };
        //TODO this could be made more efficant
        get_stats(id,callback,c);
      //  RequestQueue q = new Volley.newRequestQueue(c);
       // JsonObjectRequest j = new JsonObjectRequest()
    }
    public static Player get_instance()
    {
        return player_instance;
    }
    public static int getMonstersKilled(){return monstersKilled;}
    public void force_update()
    {
        get_stats(id,callback,context);
    }
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
    private void get_stats(int statsId,final Callback_handler c,Context t)
        {
            RequestQueue r =  Volley.newRequestQueue(t);
            JsonObjectRequest o = new JsonObjectRequest(Request.Method.GET,"http://cs309-sd-6.misc.iastate.edu:8080/api/stats/"+statsId,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Do something with response
                    //mTextView.setText(response.toString());

                    // Process the JSON
                    Log.i("Cycond test", "user stats request succsessful");
                    c.get_object_response(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Do something when error occurred
                            Log.i("Cycond Life", "user stats error");
                            Log.i("Cycond Life", error.toString());
                        }
                    });
        r.add(o);
    }
    private void get_users(final Callback_handler c) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        // Initialize a new JsonArrayRequest instance

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                "http://cs309-sd-6.misc.iastate.edu:8080/api/stats/",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        Log.i("Cycond test", "stats request succsessful");
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                c.get_response(response);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        Log.i("Cycond Life", "stats request error");
                        Log.i("Cycond Life", error.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
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
