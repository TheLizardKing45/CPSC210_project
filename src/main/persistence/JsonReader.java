package persistence;

import model.Record;
import model.Scoreboard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents a file reader that reads scoreboard from JSON data stored in a file
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from a source file
    public JsonReader(String source) {
        this.source = source;
    }

    // similar idea taken from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads scoreboard from file and returns it
    public Scoreboard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScoreboard(jsonObject);
    }

    // similar idea taken from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: parses scoreboard from JSON object and returns it
    private Scoreboard parseScoreboard(JSONObject jsonObject) {
        Scoreboard sb = new Scoreboard();
        addRecords(sb, jsonObject);
        return sb;
    }

    // similar idea from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: sb
    // EFFECTS: parses records from JSON object and adds them to scoreboard
    private void addRecords(Scoreboard sb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scoreboard");
        for (Object json : jsonArray) {
            JSONObject nextRecord = (JSONObject) json;
            addRecord(sb, nextRecord);
        }
    }

    // similar idea from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: sb
    // EFFECTS: creates a record object from JSON object and adds it to a scoreboard
    private void addRecord(Scoreboard sb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int totalKills = jsonObject.getInt("totalKills");
        int totalPoints = jsonObject.getInt("totalPoints");
        int totalRounds = jsonObject.getInt("totalRounds");
        Record record = new Record(name, totalKills, totalPoints, totalRounds);
        sb.addRecord(record);
    }

    // EFFECTS: returns contents of a source file as a string
    // throws IOException if an error occurs while reading file
    private String readFile(String source) throws IOException {
        String fileData;
        try {
            List<String> fileLines = Files.readAllLines(Paths.get(source), StandardCharsets.UTF_8);
            fileData = String.join("", fileLines);
        } catch (IOException e) {
            throw new IOException();
        }
        return fileData;
    }


}
