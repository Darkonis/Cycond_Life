package com.example.cycondlife.game;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.cycondlife.Dice;
import com.example.cycondlife.communication.Callback_handler;
import com.example.cycondlife.communication.ChatSender;
import com.example.cycondlife.communication.Json_handler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * stores information about the players current state in a singlton format
 */
public class Player extends Character {
    private static Player player_instance;
    private static int monstersKilled;
    private final String statlink = "/api/stats/updateStat/";
    //TODO make this private
    public double sight = .002;
    private String username;
    private String password;
    private int id;
    private ChatSender sender;
    private URI chatLink;
    private Context context;
    private Callback_handler callback;
    private int experiance = 0;
    private int level = 1;

    private int hitChance = 100;
    private double critChance = 1;
    private double critMult = 2;
    private double dmgReduct = .1;
    private double BS = 10;
    private int tinkPoints = 50;
    private int tinkPointsMax = 50;
    private double tinkMult = 1.0;
    private double dodgeChance = 15;
    private int tinkeringPoints = -1;
    private ArrayList<Consumable> inv = new ArrayList<>();
    private ArrayList<Consumable> activeItems = new ArrayList<>();
    private int itemCount = 0;

    private Player() {
        super();
    }


    private Player(String user, int idt) {
        super();
        update_substats();
        this.id = idt;
        this.username = user;
    }

    private Player(String user, int idt, Context c) {
        super();
        update_substats();

        username = user;
        name = user;

        //Connect to chat websocket for persistent chat
        try {
            chatLink = new URI("ws://cs309-sd-6.misc.iastate.edu:8080/websocket/" + username);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        sender = new ChatSender();
        sender.connectWebSocket(chatLink);

        this.id = idt;
        this.context = c;
        callback = new Callback_handler() {
            @Override
            public void get_array_response(JSONArray a) {
                for (int i = 0; i < a.length(); i++) {
                    try {


                        if (a.getJSONObject(i).getInt("accountId") == id) {
                            get_stats(a.getJSONObject(i).getInt("id"), this, context);
                        }
                    } catch (Exception e) {
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
                    BS = presentation + critical_thinking;
                    resolve = presentation;
                } catch (Exception e) {
                    Log.i("Cycond Life", "Stat pull error");
                }
            }
        };
        //TODO this could be made more efficant
        get_stats(id, callback, c);
        //  RequestQueue q = new Volley.newRequestQueue(c);
        // JsonObjectRequest j = new JsonObjectRequest()
    }

    /**
     * create a generic test case for running tests
     * IMPORTANT DO NO USE ANYWHERE ELSE
     *
     * @param user
     * @param idt
     */
    public static synchronized void createTestInstance(String user, int idt) {
        player_instance = new Player(user, idt);
    }

    /**
     * get the current instance of the player class
     *
     * @return the only instance
     */
    public static Player get_instance() {
        return player_instance;
    }

    public static int getMonstersKilled() {
        return monstersKilled;
    }

    /**
     * Create the singlton player
     *
     * @param user the username
     * @param id   the user id
     * @param c    the context
     */
    public static synchronized void create_the_instance(String user, int id, Context c) {
        if (player_instance != null) {
            return;
        }
        player_instance = new Player(user, id, c);
    }

    /**
     * nullify the player class (Note may break singlton pattern use with caution)
     */
    public static synchronized void destroy_the_instance() {
        player_instance = null;
    }

    public int getHitChance() {
        return hitChance;
    }

    public double getSight() {
        return sight;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public double getCritChance() {
        return critChance;
    }

    public double getCritMult() {
        return critMult;
    }

    public double getDmgReduct() {
        return dmgReduct;
    }
    /*
    should be used only for test methods
     */

    public double getBS() {
        return BS;
    }

    public int getTinkPoints() {
        return tinkPoints;
    }

    public double getTinkMult() {
        return tinkMult;
    }

    public double getTinkeringPoints() {
        //  return tinkeringPoints;
        return tinkPoints;
    }

    public int getLevel() {
        return level;
    }

    /**
     * give the player experiance
     *
     * @param val
     */
    public void incEXP(int val) {
        experiance += val;
    }

    public int getCreativity() {
        return creativity;
    }

    public int getId()  { return id;}

    /**
     * force an update of the player statistics
     */
    public void force_update() {
        get_stats(id, callback, context);
    }

    /**
     * add an item to the active effects table
     *
     * @param i item to add
     */
    public void addActiveItem(Item i) {
        activeItems.add((Consumable) i);
    }

    public ArrayList<Consumable> getActives() {
        return activeItems;
    }

    public ArrayList<Consumable> getInv() {
        return inv;
    }

    /**
     * add an item to the players inventory note the inventory cap of 20
     *
     * @param i item to add
     */
    public void addItem(Consumable i) {
        //TODO propagate to the server when possuible
        if (inv.size() < 20) {
            inv.add(i);
        } else {
            Log.i("Cycond Info", "You drop some items");
        }
    }

    /**
     * safely remove an item from the inventory
     *
     * @param index
     * @return the item that was removed
     */
    public Item removeItem(int index) {
        return inv.remove(index);
    }

    /**
     * sort the the active item list and apply needed effects
     */
    public void parseActives() {
        for (int i = 0; i < activeItems.size(); i++) {
            Consumable c = activeItems.get(i);
            switch (c.type) {
                case Consumable.creativity: {
                    Log.i("Cycond Info", "Creativity: " + creativity);
                    creativity += c.getEffect().roll();
                    Log.i("Cycond Info", "Creativity: " + creativity);
                    break;
                }
                case Consumable.presentation: {
                    Log.i("Cycond Info", "presentation: " + presentation);
                    presentation += c.getEffect().roll();
                    Log.i("Cycond Info", "presentation: " + presentation);
                    break;
                }
                case Consumable.criticalThinking: {
                    Log.i("Cycond Info", "criticalThinking: " + critical_thinking);
                    critical_thinking += c.getEffect().roll();
                    Log.i("Cycond Info", "criticalThinking: " + critical_thinking);

                    break;
                }
                default:
                    Log.i("Cycond Error", "unhandled item type please contact the developer");
                    break;

            }

        }
    }

    /**
     * calculates the player substats base on primary stats
     */
    //TODO max resolve
    public void update_substats() {
        parseActives();
        hitChance = 50 + creativity + critical_thinking;
        if (hitChance > 99) hitChance = 99;
        sight = .001 + (critical_thinking + 0.0) / 10000;
        critChance = 1 + ((critical_thinking + creativity) / 1000.0) * 9;
        critMult = 2 + (presentation + critical_thinking) / 500.0;
        dmgReduct = .01 + (presentation + critChance) / 100.0;
        BS = (presentation + critical_thinking) / 100.0;
        tinkPointsMax = (int) Math.round(1.5 * critical_thinking);
        tinkMult = .9 + (creativity + critical_thinking) / 1500.0;
        dodgeChance = 15 + (creativity / 2000.0);
        if (tinkeringPoints == -1) tinkeringPoints = tinkPointsMax;
    }

    private void get_stats(int statsId, final Callback_handler c, Context t) {
        RequestQueue r = Volley.newRequestQueue(t);
        JsonObjectRequest o = new JsonObjectRequest(Request.Method.GET, "http://cs309-sd-6.misc.iastate.edu:8080/api/stats/" + statsId, null, new Response.Listener<JSONObject>() {
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
                                c.get_array_response(response);
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

    /**
     * get the chat sender
     *
     * @return the chat sender
     */
    public ChatSender getSender() {
        return sender;
    }

    /**
     * updates the players Resolve
     *
     * @param dmg the amount of dmg taken
     * @param c   the context
     */
    public void take_dmg(int dmg, Context c) {
        resolve = this.resolve - dmg;
        Json_handler j = new Json_handler(c);
        j.update_stat(Player.get_instance().id, "resolve", resolve);
    }
    /*
    TODO adjust this to update locally as well
     */

    protected void update_stat(int val, String stat, Context c) {
        Json_handler j = new Json_handler(c);
        j.update_stat(id, stat, val);
    }

    /**
     * change the players health
     *
     * @param i new value
     */
    public void changeResolve(int i) {
        resolve += i;
        if (resolve >= 100) {
            resolve = 100;
        }
        Json_handler j = new Json_handler(context);
        j.update_stat(Player.get_instance().id, "resolve", resolve);
    }

    /**
     * adjust the number of tinkering points you have
     *
     * @param i amount to change by
     */
    public void adjustTinkeringPoints(int i) {
        tinkeringPoints += i;
        if ((int) Math.round(1.5 * critical_thinking) < tinkeringPoints)
            tinkeringPoints = (int) Math.round(1.5 * critical_thinking);
    }

    /**
     * end an items effect on the player
     *
     * @param c the item to end
     */
    public void endItem(Consumable c) {
        switch (c.type) {
            case Consumable.creativity: {
                creativity -= c.getEffect().roll();
                break;
            }
            case Consumable.presentation: {
                presentation -= c.getEffect().roll();
                break;
            }
            case Consumable.criticalThinking: {
                critical_thinking -= c.getEffect().roll();

                break;
            }
            default:
                Log.i("Cycond Error", "unhandled item type please contact the developer");
                break;

        }
    }

}
