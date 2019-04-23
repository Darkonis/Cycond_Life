package com.example.cycondlife;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Item {
    protected int itemID;
    protected String name;
    protected String description;
    protected int type;
    protected static ArrayList<Item> itemList= new ArrayList<>();
    public int getType(){
        return type;
    }
    public int getItemID()
    {
        return itemID;
    }
    /*
    finds an item on the item list by its id
     */
    public static Item findByID(int ID)
    {
        for(int i=0;i<itemList.size();i++)
        {
            if(itemList.get(i).itemID==ID);
            {
                return itemList.get(i);
            }
        }
        return itemList.get(0);
    }
    public static void pullItemList()
    {
        itemList = new ArrayList<>();
        Callback_handler c = new Callback_handler() {
            @Override
            public void get_response(JSONArray q) {
                for(int i=0;i< q.length();i++)
                {
                    try {
                    JSONObject a= q.getJSONObject(i);

                        Item l = new Consumable(a.getInt("itemID"), a.getString("name"), a.getString("description"),
                                a.getInt("type"), new Dice (a.getString("effect")), a.getInt("duration"), a.getString("useMSG"));
                        itemList.add(l);
                    }
                    catch (Exception e)
                    {
                        Log.i("Cycond Error","ItemList request parse error");
                    }
                }
            }

            @Override
            public void get_object_response(JSONObject o) {
                return;
            }
        };

        Json_handler.makeCall(Request.Method.GET,"Get a url",c,1,null);
    }
}
