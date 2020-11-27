package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Unit tests for the Bullet class
 */

public class BulletTest {
    Bullet b;
    Bullet b2;

    @BeforeEach
    void runBefore() {
        b = new Bullet(30, -1);
        b2 = new Bullet(45, 1);
    }

    @Test
    void testConstructor() {
        assertEquals(30, b.getPosX());
        assertEquals(45, b2.getPosX());
    }

    @Test
    void testMove() {
        b.move();
        b2.move();
        assertEquals(30 - Bullet.DX, b.getPosX());
        assertEquals(45 + Bullet.DX, b2.getPosX());
    }


}
