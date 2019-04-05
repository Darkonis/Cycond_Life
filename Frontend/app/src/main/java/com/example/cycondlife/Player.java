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

import java.util.ArrayList;

import static com.android.volley.toolbox.Volley.newRequestQueue;

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
    private Context context;
    private Callback_handler callback;
    private int experiance=0;
    private int level =1;

    private int hitChance =100;
    //TODO make this private
    public double sight =.002;
    private double critChance =1;
    private double critMult =2;
    private double dmgReduct =.1;
    private double BS =10;
    private int tinkPoints=50;
    private double tinkMult=1.0;
    private double dodgeChance=15;
    private ArrayList<Item> inv = new ArrayList<>();

    private int itemCount=0;


    public int getHitChance() {
        return hitChance;
    }
    public double getSight()
    {
        return sight;
    }
    public double getDodgeChance()
    {
        return dodgeChance;
    }
    public double getCritChance()
    {
        return critChance;
    }
    public double getCritMult()
    {
        return critMult;
    }
    public double getDmgReduct()
    {
        return  dmgReduct;
    }
    public double getBS()
    {
        return BS;
    }
    public int getTinkPoints()
    {
        return tinkPoints;
    }
    public double getTinkMult()
    {
        return tinkMult;
    }
    public int getLevel() {
        return level;
    }
    public void incEXP(int val)
    {
        experiance+=val;
    }


    private Player(String user, int idt, Context c)
    {
        super();
        Item.itemList.add(new Consumable(0,"lesser health potion","This potion sits in a red bottle labeled TEST",0,new Dice("2+2d4"),0,"You take a health Potion"));
        update_substats();
        username=user;
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

    public ArrayList<Item> getInv() {
        return inv;
    }
    public void addItem(Item i)
    {
        //TODO propagate to the server when possuible
        if(inv.size()<20)
        {
            inv.add(i);
        }
        else
        {
            Log.i("Cycond Info", "You drop some items");
        }
    }
    public Item removeItem(int index)
    {
        return inv.remove(index);
    }
    public void update_substats()
    {
        hitChance =50+creativity+critical_thinking;
        if(hitChance >99) hitChance =99;
        sight =.001+(critical_thinking+0.0)/10000;
        critChance=1+((critical_thinking+creativity)/1000.0)*9;
        critMult= 2+ (presentation+critical_thinking)/500.0;
        dmgReduct = .01 +(presentation+critChance)/100.0;
        BS=(presentation+critical_thinking)/100.0;
        tinkPoints=(int) Math.round(1.5*critical_thinking);
        tinkMult=.9+(creativity+critical_thinking)/1500.0;
        dodgeChance= 15+(creativity/2000.0);
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
    public void changeResolve(int i)
    {
        resolve +=i;
        Json_handler j = new Json_handler(context);
        j.update_stat(Player.get_instance().id,"resolve",resolve);
    }

}
