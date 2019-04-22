package com.example.cycondlife.Communication;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Callback_handler {
    void get_response(JSONArray a);
    void get_object_response(JSONObject o);
}
