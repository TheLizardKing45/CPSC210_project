package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Record class
 */

public class RecordTest {

    @Test
    public void testRecord() {
        Record r1 = new Record("Brett", 45, 130, 30);
        Record r2 = new Record("Shrek", 10, 30, 8);
        assertEquals(45, r1.getTotalKills());
        assertEquals(30, r2.getTotalPoints());
        assertEquals(8, r2.getTotalRounds());
    }

}
