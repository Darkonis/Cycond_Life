package edu.se309.app.db.building;

import java.util.ArrayList;

protected class Way {

    private ArrayList<Node> nodes;

    private long id;

    private String name;

    protected Way(long id, ArrayList<Node> nodes, String name) {
        this.nodes = nodes;
        this.id = id;
        this.name = name;
    }

    protected long getId() {
        return id;
    }

    protected String getName() {
        return name;
    }

    protected ArrayList<Node> getNodes() {
        return nodes;
    }

    protected String nodeStringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodes.size(); i++) {
            sb.append(nodes.get(i).toString() + ",\n");
        }
        sb.append(nodes.get(0).toString());
        return sb.substring(0, sb.length());
    }

    @Override
    protected String toString() {
        String outputNodes = nodeStringBuilder();
        return "INSERT INTO building_locations (building_name,geo,earned_stat)" + " VALUES (" + name
                + ",ST_GeomFromText('POLYGON((" + outputNodes + "))',4326)," + Relation.getRandomStat(name) + ");";
    }
}
