package persistence;

import model.Record;
import model.Scoreboard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the JsonReader class
 */
public class JsonReaderTest extends JsonTest {

    // from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testReadFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Scoreboard sb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testReadEmptyScoreboard() {
        JsonReader reader = new JsonReader("./data/testReadEmptyScoreboard.json");
        try {
            Scoreboard sb = reader.read();
            assertEquals(0, sb.scoreboardSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testReadNonEmptyScoreboard() {
        JsonReader reader = new JsonReader("./data/testReadNonEmptyScoreboard.json");
        try {
            Scoreboard sb = reader.read();
            List<Record> records = sb.getScoreboard();
            assertEquals(3, records.size());
            checkRecord("Brett", 80, 800, 40, records.get(0));
            checkRecord("Raoul", 14, 140, 10, records.get(1));
            checkRecord("AAA", 1, 1, 1, records.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
