package com.example.cycondlife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //pull_monster_map();
        //button object creations
        Button stats = findViewById(R.id.stats);
        Button inventory = findViewById(R.id.inventory);
        Button shop = findViewById(R.id.shop);
        Button friends = findViewById(R.id.friends);
        Button map = findViewById(R.id.map);
        Button dev_menu = findViewById(R.id.dev_menu);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openStats= new Intent(menu.this,stats_menu.class);
                startActivity(openStats);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Cycond Life", "Attempt to open map from main menu");
                //Toast.makeText(getApplicationContext(), "Opening map...", Toast.LENGTH_SHORT);
                Intent openMap = new Intent(menu.this, MapsActivity.class);
                startActivity(openMap);
            }
        });

        dev_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Cycond Life", "Attempt to open dev menu");
                Intent openDevMenu = new Intent(menu.this, dev_menu.class);
                startActivity(openDevMenu);
            }
        });


    }
    private void pull_monster_map()
    {
        Context c= getApplicationContext();
        final RequestQueue requestQueue = Volley.newRequestQueue(c);
        // Initialize a new JsonArrayRequest instance

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                "http://cs309-sd-6.misc.iastate.edu:8080/api/monster/list",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        Log.i("Cycond test", "request succsessful");
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                String[] out = new String[response.length()];
                                for (int k = 0; k < response.length(); k++) {
                                    out[k] = response.get(k).toString();

                                    Log.i("Cycond life", "onResponse: " + out[k]);
                                }
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
                        Log.i("Cycond Life", "request error");
                        Log.i("Cycond Life", error.getLocalizedMessage());
                    }
                }
        );


        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);


    }
    /* public void setActivityBackgroundColor(int color) {      //Can possibly be used to change background color of main menu
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }   */


}
