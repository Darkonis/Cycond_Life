package com.example.cycondlife.communication;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
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

import com.android.volley.toolbox.JsonObjectRequest;

//TODO look into making this a super class to other activitys
public class   Json_handler {

    private String user,pass,first,last,email,type;
    public final String mJSONBASEString = "http://cs309-sd-6.misc.iastate.edu:8080/api/";
    public final String mAccountString ="accounts/";
    private Context mContext;
    JSONArray a;
    private JSONObject o;
    volatile boolean done;
    private int user_id;
   volatile ArrayList<String[]> t;
    private final String statlink="http://cs309-sd-6.misc.iastate.edu:8080/api/stats/updateStat/";
    private final String statlinkadd="http://cs309-sd-6.misc.iastate.edu:8080/api/stats/add/";
    private static RequestQueue queue;
    /**
     * create the json handler
     * @param c the context of the app
     */
    public Json_handler(Context c)
    {
        mContext =c;
        queue= Volley.newRequestQueue(c);
    }

    /**
     * delete a user by id
     * @param user_id
     */
    public void delete_user(int user_id)
    {
        this.user_id=user_id;
        delete_user d=new delete_user();
        d.execute();
    }

    /**
     * add stats to a given account
     * @param id the id to get stats
     */
    public void add_stats(int id)
    {
        final RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject j = new JSONObject();
        try {
            j.accumulate("accountID", id);
            j.accumulate("presentation", 10);
            j.accumulate("creativity",10);
            j.accumulate("critical thinking",10);
            JsonObjectRequest t=new JsonObjectRequest(Request.Method.POST, statlinkadd, j, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("Cycond Response",response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Cycond Error","add stats Error");
                    Log.i("Cycond Error",error.toString());
                }
            });
            requestQueue.add(t);
        }
        catch (Exception e)
        {
            Log.i("Cycond Error",e.toString());
        }
    }

    /**
     * update a stat to a given value
     * @param id the user id
     * @param stat name of the stat
     * @param value the new value
     */
    public void update_stat(int id, String stat,int value)
    {
       // this.getApplicationContext();
        if (value<0) value=0;
        JSONObject j = new JSONObject();
        final RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest jsonMain = new JsonObjectRequest(Request.Method.PUT, statlink + id + "/"+"{stat}/{value}?stat="+stat+"&value="+value , j, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {

                    Log.i("Cycond Life Json",response.toString());
                }
                catch(Exception e)
                {
                    Log.i("Cycond Error", "Error sending stats");

                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e)
            {
                Log.i("Cy Error", "error posting stat change");
                //e.printStackTrace();
            }
        }) ;


        requestQueue.add(jsonMain);
    }

    /**
     * create a user
     * @param user the username
     * @param pass the password
     * @param first first name
     * @param last last name
     * @param email email
     * @param type account permissions
     * @return succsess
     */
    public boolean send_new_user(String user, String pass,String first,String last,String email,String type)
    {
        this.user=user;
        this.pass =pass;
        this.first=first;
        this.last = last;
        this.email=email;
        if(!(type.equals("admin")||type.equals("user"))) return false;
        this.type=type;
        Add_user a=new Add_user();
        a.execute();
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
                URL url = new URL(mJSONBASEString+ mAccountString+user_id);
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
                e.printStackTrace();

            }
            return "deleted";
        }
    }
    private class Add_user extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL("http://cs309-sd-6.misc.iastate.edu:8080/api/accounts/add");
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
    /**
    make a generic Json call
     type should be Request.Method.(this)
     retType should be 0 for object
     1 for array
     anything else is currently unsupported and would need to be added
     */
    public static void makeCall(int type ,String url,final Callback_handler c,int retType, @Nullable JSONObject[] sendObjects)
    {
        JSONArray ar=  new JSONArray();
        if(sendObjects!=null && sendObjects.length>1) {
            for (int i = 0; i < sendObjects.length; i++) {
                ar.put(sendObjects[i]);
            }
        }
        else
        {
            ar=null;
        }
        JsonRequest t =null;
        if(retType==1)
        {
            t = new JsonArrayRequest(type, url, ar, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    c.get_array_response(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Cycond Error","Generic Json Request error");
                }
            });

        }
        else if(retType ==0)
        {
             t = new JsonObjectRequest(type, url, sendObjects[0], new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                     c.get_object_response(response);
                 }
             }, new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                     Log.i("Cycond Error","Generic Json Request error");
                 }
             });
        }
        if(t !=null) {
            queue.add(t);
        }
    }
}
