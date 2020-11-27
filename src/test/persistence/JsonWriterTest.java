package persistence;

import model.Record;
import model.Scoreboard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for JsonWriter Class
 */
public class JsonWriterTest extends JsonTest {

    // test from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testWriterInvalidFile() {
        try {
            Scoreboard sb = new Scoreboard();
            sb.addRecord(new Record("test1", 15, 170, 150));
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // test from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testWriterEmptyScoreboard() {
        try {
            Scoreboard sb = new Scoreboard();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyScoreboard.json");
            writer.open();
            writer.write(sb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyScoreboard.json");
            sb = reader.read();
            assertEquals(0, sb.scoreboardSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // test from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testWriterNoExceptionThrown() {
        try {
            Scoreboard sb = new Scoreboard();
            sb.addRecord(new Record("test2", 67, 680, 36));
            sb.addRecord(new Record("test6", 6, 60, 3));
            JsonWriter writer = new JsonWriter("./data/testWriterNoExceptionThrown.json");
            writer.open();
            writer.write(sb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNoExceptionThrown.json");
            sb = reader.read();
            List<Record> records = sb.getScoreboard();
            assertEquals(2, sb.scoreboardSize());
            checkRecord("test2", 67, 680, 36, records.get(0));
            checkRecord("test6", 6, 60, 3, records.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
