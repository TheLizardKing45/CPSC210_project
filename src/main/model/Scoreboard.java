package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the scoreboard that contains multiple records
 */


public class Scoreboard implements Writeable {

    private List<Record> scoreboard;

    // EFFECTS: creates a new scoreboard object
    public Scoreboard() {
        scoreboard = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds new record to scoreboard if the name in the record is not already in the scoreboard
    public void addRecord(Record r) {
        scoreboard.add(r);
    }

    // MODIFIES: this
    // EFFECTS: deletes record with the given name from scoreboard if it exists, otherwise do nothing
    public void deleteRecord(int index) {
        scoreboard.remove(index);
    }

    public List<Record> getScoreboard() {
        return scoreboard;
    }

    // EFFECTS: return the size of the scoreboard
    public int scoreboardSize() {
        return scoreboard.size();
    }

    // similar method from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: converts scoreboard into a JSON object to be written to destination file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("scoreboard", recordsToJson());
        return json;
    }

    // similar method from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: converts records in scoreboard into JSON objects and puts them in a JSON array
    private JSONArray recordsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Record r : scoreboard) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
