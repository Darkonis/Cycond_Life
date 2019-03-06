package com.example.cycondlife;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.webkit.JsResult;

import com.android.volley.toolbox.JsonObjectRequest;

//TODO look into making this a super class to other activitys
public class Json_handler {

    private String user,pass,first,last,email,type;
    private String mJSONURLString = "http://cs309-sd-6.misc.iastate.edu:8080/api/accounts";
    private Context mContext;
    JSONArray a;
    private JSONObject o;
    volatile boolean done;
    private int user_id;
   volatile ArrayList<String[]> t;
    Json_handler(Context c)
    {
        mContext =c;
    }
    public void delete_user(int user_id)
    {
        this.user_id=user_id;
        delete_user d=new delete_user();
        d.execute();
    }
    public boolean send_new_user(String user, String pass,String first,String last,String email,String type)
    {
        this.user=user;
        this.pass =pass;
        this.first=first;
        this.last = last;
        this.email=email;
        if(!(type.equals("admin")||type.equals("user"))) return false;
        this.type=type;
        Add_user asyncT = new Add_user();
        asyncT.execute();
        return true; //TODO add check for succsessful completion
    }
    private JSONObject buidJsonObject() throws JSONException {

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("username",   user);
        jsonParam.put("password",   pass);
        jsonParam.put("firstName",   first);
        jsonParam.put("lastName",   last);
        jsonParam.put("email",  email);
        jsonParam.put("accountType",  type);

        return jsonParam;
    }
    private class delete_user extends  AsyncTask<String, Void,String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL("http://cs309-sd-6.misc.iastate.edu:8080/api/accounts/" +user_id);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("DELETE");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                // conn.setDoOutput(true);
                conn.setDoInput(true);

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());


                os.flush();
                os.close();

                Log.i("Cycond Life", String.valueOf(conn.getResponseCode()));
                Log.i("Cycond Life", conn.getResponseMessage());

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Cycond Life", "Error on delete");
            }
            return "deleted";
        }
    }
    private class Add_user extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL("http://cs309-sd-6.misc.iastate.edu:8080/api/accounts/");
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
