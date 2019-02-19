package com.example.cycondlife;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dev_menu extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private Button mButtonDo;
    private TextView mTextView;
    private String mJSONURLString = "http://cs309-sd-6.misc.iastate.edu:8080/api/accounts";
    private Button call_name;
    private Button submit_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dev_menu);
        mTextView = findViewById(R.id.name);
        mTextView.setVisibility(View.GONE);
        // Get the application context
        mContext = getApplicationContext();
        mActivity = dev_menu.this;
        call_name=findViewById(R.id.call_person);
        submit_button = findViewById(R.id.submit1);
        submit_button.setVisibility(View.GONE);
        // Get the widget reference from XML layout
        mButtonDo = findViewById(R.id.call_names);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                    1);
        }
        call_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            submit_button.setVisibility(View.VISIBLE);
                           mTextView.setVisibility(View.VISIBLE);
                    }
                });
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!mTextView.getText().toString().equals(""))
               {
                   RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                   // Initialize a new JsonArrayRequest instance
                   JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                           mJSONURLString+"/"+mTextView.getText().toString(),
                           null,
                           new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   // Do something with response
                                   //mTextView.setText(response.toString());

                                   // Process the JS
                                       // Loop through the array elements

                                           Log.i("Cycond Life", response.toString());



                               }
                           },
                           new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {
                                   // Do something when error occurred
                                   Log.i("Cycond Life", "Name request error");
                                   Log.i("Cycond Life", error.getLocalizedMessage());
                               }
                            }
                               );
                   requestQueue.add(jsonObjectRequest);
               }


            }

        });
            // Set a click listener for button widget
            mButtonDo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Empty the TextView
//                mTextView.setText("");

                    // Initialize a new RequestQueue instance
                    RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                    // Initialize a new JsonArrayRequest instance
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                            mJSONURLString,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    // Do something with response
                                    //mTextView.setText(response.toString());

                                    // Process the JSON
                                    try {
                                        // Loop through the array elements
                                        for (int i = 0; i < response.length(); i++) {
                                            // Get current json object
                                            JSONObject info = response.getJSONObject(i);
                                            Log.i("Cycond Life", info.toString());

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Do something when error occurred
                                    Log.i("Cycond Life", "request error");
                                    Log.i("Cycond Life",error.getLocalizedMessage());
                                }
                            }
                    );

                    // Add JsonArrayRequest to the RequestQueue
                    requestQueue.add(jsonArrayRequest);
                }
            });


        }
    }

