package edu.se309.app.db.building;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BuildingParserJsonToMySQL {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    HashMap<Long, Node> nodes = new HashMap<>();
    ArrayList<Way> ways = new ArrayList<>();
    ArrayList<Relation> relations = new ArrayList<>();
    // Read Node JSON file to java object
    String directory = System.getProperty("user.dir")
      + "/building_database_resources/edu/se309/app/db/building/";
    Reader reader = new FileReader(directory + "node.json");
    Gson gson = new Gson();
    JsonParser jp = new JsonParser();
    JsonElement je = jp.parse(reader);
    JsonArray ja = je.getAsJsonArray();
    for (int i = 0; i < ja.size(); i++) {
      JsonObject jo = (JsonObject) ja.get(i);
      Node n = gson.fromJson(jo, Node.class);
      nodes.put(n.getId(), n);
    }
    // Read Way JSON file to java object
    reader.close();
    reader = new FileReader(directory + "way.json");
    je = jp.parse(reader);
    ja = je.getAsJsonArray();
    for (int i = 0; i < ja.size(); i++) {
      JsonObject jo = (JsonObject) ja.get(i);
      long id = jo.get("id").getAsLong();
      JsonArray arr = jo.get("nodes").getAsJsonArray();
      String name = "null";
      if (!jo.get("name").isJsonNull()) { name = "'" + jo.get("name").getAsString() + "'"; }
      ArrayList<Node> tempNodes = new ArrayList<>();
      for (int j = 0; j < arr.size(); j++) {
        tempNodes.add(nodes.get(arr.get(j).getAsLong()));
      }
      Way w = new Way(id, tempNodes, name);
      ways.add(w);
    }
    // Read Relation JSON file to java object
    reader.close();
    reader = new FileReader(directory + "relation.json");
    je = jp.parse(reader);
    ja = je.getAsJsonArray();
    for (int i = 0; i < ja.size(); i++) {
      JsonObject jo = (JsonObject) ja.get(i);
      long id = jo.get("id").getAsLong();
      JsonArray arr = jo.get("members").getAsJsonArray();
      String name = "null";
      if (!jo.get("name").isJsonNull()) { name = "'" + jo.get("name").getAsString() + "'"; }
      Way outer = null;
      ArrayList<Way> inner = new ArrayList<>();
      for (int j = 0; j < arr.size(); j++) {
        JsonObject member = arr.get(j).getAsJsonObject();
        String role = member.get("role").getAsString();
        long wId = member.get("ref").getAsLong();
        Way tempWay = null;
        for (Way w : ways) {
          if (w.getId() == wId) {
            tempWay = w;
            break;
          }
        }
        if (role.equals("outer")) {
          outer = tempWay;
        } else {
          inner.add(tempWay);
        }
      }
      relations.add(new Relation(id, outer, inner, name));
    }
    // Writes an SQL file
    FileWriter f = new FileWriter(directory + "building_name_gen.sql");
    for (Way w : ways) {
      f.write(w.toString() + "\n\n");
    }
    for (Relation r : relations) {
      f.write(r.toString() + "\n\n");
    }
    f.close();
    System.out.println("DONE!");
    reader.close();
  }
}
