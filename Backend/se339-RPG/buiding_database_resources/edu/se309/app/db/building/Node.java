package edu.se309.app.db.building;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Node {
	
	
	@SerializedName("id")
	@Expose
	private long id;
	@SerializedName("lat")
	@Expose
	private double lat;
	@SerializedName("lon")
	@Expose
	private double lon;	

	public Node(long id, double lat, double lon) {		
		this.lon = lon;
		this.lat = lat;
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public double getLat() {
		return lat;
	}
	public double getLon() {
		return lon;
	}
	
	@Override
	public String toString() {
		return String.valueOf(lon) + " " + String.valueOf(lat);
	}
	
	
	
	

}
