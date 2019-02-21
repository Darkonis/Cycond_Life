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
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class dev_menu extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private Button mButtonDo;
    private TextView mTextView;
    private String mJSONURLString = "http://cs309-sd-6.misc.iastate.edu:8080/api/accounts";
    private Button call_name;
    private Button submit_button;
    private Button add_user;
    private Button submit2;
    private TextView user;
    private TextView pass;
    private TextView first;
    private TextView last;
    private TextView email;
    private TextView type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dev_menu);
        mTextView = findViewById(R.id.name);
        mTextView.setVisibility(View.GONE);
        // Get the application context
        mContext = getApplicationContext();
        mActivity = dev_menu.this;
        call_name = findViewById(R.id.call_person);
        submit_button = findViewById(R.id.submit1);
        submit_button.setVisibility(View.GONE);
        // Get the widget reference from XML layout
        mButtonDo = findViewById(R.id.call_names);
        add_user = findViewById(R.id.Add_User);
        submit2 = findViewById(R.id.submit2);
        user = findViewById(R.id.user_name);
        pass = findViewById(R.id.password);
        first = findViewById(R.id.first_name);
        last = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        type = findViewById(R.id.account_type);
        hide_entries();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                    1);
        }
        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide_entries();
                user.setVisibility(View.VISIBLE);
                pass.setVisibility(View.VISIBLE);
                first.setVisibility(View.VISIBLE);
                last.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                type.setVisibility(View.VISIBLE);
                submit2.setVisibility(View.VISIBLE);
            }
        });
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HTTPAsyncTask asyncT = new HTTPAsyncTask();
                asyncT.execute();

            }
        });
        call_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide_entries();
                submit_button.setVisibility(View.VISIBLE);
                mTextView.setVisibility(View.VISIBLE);
            }
        });
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTextView.getText().toString().equals("")) {
                    RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                    // Initialize a new JsonArrayRequest instance
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                            mJSONURLString + "/" + mTextView.getText().toString(),
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
                                Log.i("Cycond Life", error.getLocalizedMessage());
                            }
                        }
                );

                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);
            }
        });


    }

    void hide_entries() {
        submit_button.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
        submit2.setVisibility(View.GONE);
        user.setVisibility(View.GONE);
        pass.setVisibility(View.GONE);
        first.setVisibility(View.GONE);
        last.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        type.setVisibility(View.GONE);
    }

    public JSONObject buidJsonObject() throws JSONException {

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("username", " " + user.getText().toString());
        jsonParam.put("password", " " + pass.getText().toString());
        jsonParam.put("firstName", " " + first.getText().toString());
        jsonParam.put("lastName", " " + last.getText().toString());
        jsonParam.put("email", " " + email.getText().toString());
        jsonParam.put("accountType",  type.getText().toString());

        return jsonParam;
    }

    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL("http://cs309-sd-6.misc.iastate.edu:8080/api/accounts");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = buidJsonObject();

                Log.i("Cycond Life", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                Log.i("Cycond Life", String.valueOf(conn.getResponseCode()));
                Log.i("Cycond Life", conn.getResponseMessage());

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Cycond Life", "Error on push");
            }
            return "cool";
        }
    }
}

