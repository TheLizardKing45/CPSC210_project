package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Player class
 */

public class PlayerTest {

    Player p1;

    @BeforeEach
    public void runBefore() {
        p1 = new Player();
    }

    @Test
    public void testMoveRight() {
        int initialPosition = p1.getPosX();
        p1.move(1);
        assertEquals(initialPosition + Player.getDX(), p1.getPosX());
    }

    @Test
    public void testMoveLeft() {
        int initialPosition = p1.getPosX();
        p1.move(-1);
        assertEquals(initialPosition - Player.getDX(), p1.getPosX());
    }

    @Test
    public void testDontMove() {
        int initialPosition = p1.getPosX();
        p1.move(0);
        assertEquals(initialPosition, p1.getPosX());
    }

    @Test
    public void testHandleLeftBoundary() {
        p1.setPosX(0);
        p1.move(-1);
        assertEquals(0, p1.getPosX());

    }

    @Test
    public void testHandleRightBoundary() {
        p1.setPosX(Game.WIDTH);
        p1.move(1);
        assertEquals(Game.WIDTH, p1.getPosX());
    }

    @Test
    public void testCollidedWith() {
        Zombie zombie = new Zombie();
        assertFalse(p1.collidedWith(zombie));

        Zombie zombie2 = new Zombie();
        zombie2.setPosX(p1.getPosX());
        assertTrue(p1.collidedWith(zombie2));
    }


}
