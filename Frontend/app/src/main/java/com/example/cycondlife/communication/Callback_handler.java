package com.example.cycondlife.communication;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Callback_handler {
    void get_response(JSONArray a);
    void get_object_response(JSONObject o);
}
