package edu.se309.app.db.building;

import java.util.ArrayList;

public class Relation {

	private long id;

	private Way outer;

	private ArrayList<Way> inner;

	private String name;

	public Relation(long id, Way outer, ArrayList<Way> inner, String name) {		
		this.id = id;
		this.outer = outer;
		this.inner = inner;
		this.name = name;
	}

	public long getId() {
		return id;
	}
	public ArrayList<Way> getInner() {
		return inner;
	}
	public String getName() {
		return name;
	}
	public Way getOuter() {
		return outer;
	}

	private String innerStringBuilder() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < inner.size(); i++) {
			sb.append(",\n(" + inner.get(i).nodeStringBuilder() + ")");			
		}		
		return sb.substring(0, sb.length());
	}
	
	@Override
	public String toString() {
		String innerNodes = innerStringBuilder();
		return "INSERT INTO building_locations (building_name,geo)"
				+ " VALUES (" + name + ",ST_GeomFromText('POLYGON((" + outer.nodeStringBuilder() +")" + innerNodes + ")',4326));";				
	}
	
	

}
