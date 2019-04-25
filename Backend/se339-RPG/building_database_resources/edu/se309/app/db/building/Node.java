package edu.se309.app.db.building;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

protected class Node {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("lat")
    @Expose
    private double lat;

    @SerializedName("lon")
    @Expose
    private double lon;

    protected Node(long id, double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
        this.id = id;
    }

    protected long getId() {
        return id;
    }

    protected double getLat() {
        return lat;
    }

    protected double getLon() {
        return lon;
    }

    @Override
    protected String toString() {
        return String.valueOf(lat) + " " + String.valueOf(lon);
    }
}
