package persistence;

import model.Scoreboard;
import org.json.JSONObject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Represents a writer that writes the JSON representation of a scoreboard to a file
 */

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;


    // EFFECTS: constructs a writer to write to a destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // similar idea from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // similar idea from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(Scoreboard sb) {
        JSONObject json = sb.toJson();
        saveToFile(json.toString(TAB));
    }

    // method from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // method from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
