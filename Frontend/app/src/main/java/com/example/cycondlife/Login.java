package com.example.cycondlife;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
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
    private TextView fail;
    private Button adLogin;
    private Button create;
    private String JSONURL = "http://cs309-sd-6.misc.iastate.edu:8080/api/accounts/";
    private Context thisContext;

    MediaPlayer mediaPlayer;    //used to play music when the app is opened, currently handled outside of onCreate


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        create = findViewById(R.id.create);
        submit = findViewById(R.id.loginBut);
        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        fail = findViewById(R.id.loginFail);
        adLogin = findViewById(R.id.adminLogin);

        thisContext = getApplicationContext();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                    1);
        }

        fail.setVisibility(View.INVISIBLE);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent openCreate = new Intent(Login.this,CreateAccount.class);
                startActivity(openCreate);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    check_validity();
            }
        });

        adLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Will save a temp account that can be accessed by the rest of the app in later iterations
                Player.create_the_instance("Over9000",13,getApplicationContext());
                final Intent openMenu = new Intent(Login.this, menu.class);
                startActivity(openMenu);
            }
        });
    }
    private void check_validity()
    {

        final String userName = name.getText().toString();
        final String userPass = pass.getText().toString();
        final Intent openMenu = new Intent(Login.this, menu.class);
        final Callback_handler c = new Callback_handler() {
            @Override
            public void get_response(JSONArray a) {
                return;
            }

            @Override
            public void get_object_response(JSONObject o) {
                try {
                    if (userPass.equals( o.get("password"))) {
                        Player.create_the_instance(userName,o.getInt("id"),getApplicationContext());
                        startActivity(openMenu);
                    }
                }
                catch (Exception e)
                {
                    Log.i("Cycond Life", "parse error");
                    Log.i("Cycond Life", e.getLocalizedMessage());
                }

            }
        };


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
                                String nameToCheck = info.get("username").toString();
                                String passToCheck = info.get("password").toString();

                                        if(nameToCheck.equals(userName) && passToCheck.equals(userPass))    {
                                            if(Player.get_instance()!=null) {
                                                Player.destroy_the_instance(); //remove the previous player if needed
                                            }
                                            Player.create_the_instance(userName,info.getInt("id"),getApplicationContext()); //on good login create the player object

                                            startActivity(openMenu);
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                        fail.setVisibility(View.VISIBLE);   //Should only display on codition of fail, will be changed
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


    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    @Override
    protected void onResume()   {
        super.onResume();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.test_song);
        mediaPlayer.start();
    }



    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
    }


}
