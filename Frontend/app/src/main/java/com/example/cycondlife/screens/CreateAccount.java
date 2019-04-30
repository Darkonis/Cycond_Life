package com.example.cycondlife.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cycondlife.R;
import com.example.cycondlife.communication.Json_handler;
import com.example.cycondlife.game.Player;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The create account page
 */
public class CreateAccount extends AppCompatActivity {
    private Button submit;
    private TextView user;
    private TextView pass;
    private TextView email;
    private TextView first;
    private TextView last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        submit = findViewById(R.id.finish);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        first = findViewById(R.id.first);
        last = findViewById(R.id.last);
        email = findViewById(R.id.email);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        String JSONURL = "http://cs309-sd-6.misc.iastate.edu:8080/api/accounts/";
        Json_handler j = new Json_handler(getApplicationContext());
        j.send_new_user(user.getText().toString(), pass.getText().toString(), first.getText().toString(), last.getText().toString(), email.getText().toString(), "user");
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final Intent openMenu = new Intent(CreateAccount.this, menu.class);
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

                                if (Player.get_instance() != null) {
                                    Player.destroy_the_instance(); //remove the previous player if needed
                                }
                                Json_handler j = new Json_handler(getApplicationContext());
                                j.add_stats(info.getInt("id"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Should only display on codition of fail, will be changed
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


}
