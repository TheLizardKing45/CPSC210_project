package persistence;


import model.Record;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * For testing that a record is in a file
 */
public class JsonTest {

    // similar method from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: checks if the information in the record is the same as the expected information passed in the method
    protected void checkRecord(String name, int totalKills, int totalPoints, int totalRounds, Record r) {
        assertEquals(name, r.getName());
        assertEquals(totalKills, r.getTotalKills());
        assertEquals(totalPoints, r.getTotalPoints());
        assertEquals(totalRounds, r.getTotalRounds());
    }
}
