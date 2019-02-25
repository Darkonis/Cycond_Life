package com.example.cycondlife;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {

    private Button submit;
    private TextView name;
    private TextView pass;

    private String JSONURL = "http://cs309-sd-6.misc.iastate.edu:8080/api/accounts";
    private Context thisContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        submit = findViewById(R.id.loginBut);
        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);

        thisContext = getApplicationContext();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                    1);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = name.getText().toString();
                final String userPass = pass.getText().toString();

                final Intent openMenu = new Intent(Login.this, menu.class);

                // Empty the TextView
                name.setText("");
                pass.setText("");

                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
                // Initialize a new JsonArrayRequest instance
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        JSONURL,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // Do something with response


                                // Process the JSON
                                try {
                                    // Loop through the array elements
                                    for (int i = 0; i < response.length(); i++) {
                                        // Get current json object
                                        JSONObject info = response.getJSONObject(i);
                                        String nameToCheck = info.get("username").toString();       //may not need toString, needs testing
                                        String passToCheck = info.get("password").toString();

                                        if(nameToCheck.equals(userName) && passToCheck.equals(userPass))    {
                                            startActivity(openMenu);
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //Will add text that displays in case of failure to login here
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
        });
    }
}
