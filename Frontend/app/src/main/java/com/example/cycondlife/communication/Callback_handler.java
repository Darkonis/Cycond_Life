package com.example.cycondlife.communication;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Generic Callback Handler
 */
public interface Callback_handler {
    void get_array_response(JSONArray a);

    void get_object_response(JSONObject o);
}
