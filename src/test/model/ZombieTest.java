package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Zombie class
 */

public class ZombieTest {

    Zombie z1;
    Zombie z2;

    @BeforeEach
    public void runBefore() {
        z1 = new Zombie();
        z2 = new Zombie();
        while (z2.getPosX() == z1.getPosX()) {   // ensure that the position of z2 is not the same as z1
            z2 = new Zombie();                           // for complete code coverage
        }
    }


    @Test
    public void testMoveRight() {
        int initialPosition = z1.getPosX();
        int initialPosition2 = z2.getPosX();
        z1.move(5);
        z2.move(3);
        assertEquals(initialPosition + z1.getDX(), z1.getPosX());
        assertEquals(initialPosition2 + z2.getDX(), z2.getPosX());

    }

    @Test
    public void testMoveLeft() {
        int initialPosition = z1.getPosX();
        int initialPosition2 = z2.getPosX();
        z2.move(-6);
        z1.move(-4);
        assertEquals(initialPosition - z1.getDX(), z1.getPosX());
        assertEquals(initialPosition2 - z2.getDX(), z2.getPosX());
    }

    @Test
    public void testDontMove() {
        int initialPosition = z1.getPosX();
        z2.move(0);
        assertEquals(initialPosition, z1.getPosX());
    }

    @Test
    void testCollidedWith() {
        Bullet bullet = new Bullet(z1.getPosX(), 1);
        assertTrue(z1.collidedWith(bullet));

        Bullet bullet2 = new Bullet(Game.WIDTH - 100, -1);
        assertFalse(z1.collidedWith(bullet2));

    }

}
