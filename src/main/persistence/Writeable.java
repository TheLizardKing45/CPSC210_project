package persistence;

import org.json.JSONObject;
// interface from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

/**
 * Marks classes as being able to be written as JSON objects
 */
public interface Writeable {
    JSONObject toJson();
}
