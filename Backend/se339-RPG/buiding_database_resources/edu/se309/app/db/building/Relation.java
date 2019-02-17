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

	@Override
	public String toString() {
		return "Relation [id=" + id + ", outer=" + outer + ", inner=" + inner + ", name=" + name + "]";
	}
	
	

}
