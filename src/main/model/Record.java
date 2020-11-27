package model;

import org.json.JSONObject;
import persistence.Writeable;

/**
 * holds various statistics from a finished game and is associated with the name of the player
 */


public class Record implements Writeable {

    private int totalKills;
    private int totalPoints;
    private int totalRounds;
    private String name;


    // EFFECTS: constructs a new record
    public Record(String name, int totalKills, int totalPoints, int totalRounds) {
        this.name = name;
        this.totalKills = totalKills;
        this.totalPoints = totalPoints;
        this.totalRounds = totalRounds;
    }

    public String getName() {
        return name;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    // similar method from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: converts record into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("totalKills", totalKills);
        json.put("totalPoints", totalPoints);
        json.put("totalRounds", totalRounds);
        return json;
    }
}
