package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Scoreboard class
 */

public class ScoreboardTest {
    Record r1;
    Record r2;
    Scoreboard sb1;


    @BeforeEach
    public void runBefore() {
        r1 = new Record("test", 6, 7, 3);
        r2 = new Record("lol", 13, 19, 24);
        sb1 = new Scoreboard();

    }

    @Test
    public void testAddRecord() {
        assertEquals(0, sb1.scoreboardSize());
        sb1.addRecord(r1);
        assertEquals(1, sb1.scoreboardSize());
        sb1.addRecord(r2);
        assertEquals(2, sb1.scoreboardSize());
    }


    @Test
    public void testDeleteRecord() {
        sb1.addRecord(r1);
        sb1.addRecord(r2);
        assertEquals(2, sb1.scoreboardSize());
        sb1.deleteRecord(0);
        assertEquals(1, sb1.scoreboardSize());
        sb1.deleteRecord(0);
        assertEquals(0, sb1.scoreboardSize());
    }


    @Test
    public void testGetScoreboard() {
        sb1.addRecord(r1);
        sb1.addRecord(r2);
        assertEquals(2, sb1.scoreboardSize());
        assertEquals(r1, sb1.getScoreboard().get(0));
        assertEquals(r2, sb1.getScoreboard().get(1));
    }


}
