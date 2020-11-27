package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 *  Unit tests for the Game class
 */
public class GameTest {
    Game game;

    @BeforeEach
    void runGame() {
        game = new Game();
    }

    @Test
    void testConstructor() {
        assertEquals(1, game.getZombies().size());
        assertEquals(0, game.getBullets().size());
    }

    @Test
    void testSetUp() {
        game.setCurrentRound(5);
        game.getZombies().add(new Zombie());
        game.getBullets().add(new Bullet(80,-1));
        game.setUp();
        assertEquals(1, game.getZombies().size());
        assertEquals(0, game.getBullets().size());
        assertEquals(1, game.getCurrentRound());
        assertEquals(0, game.getPoints());
        assertEquals(0, game.getKills());
        assertFalse(game.getIsOver());
        assertEquals(Gun.getMaxClipAmmo(), game.getCurrentGun().getCurrentAmmo());
        assertEquals(Gun.getMaxReserveAmmo(), game.getCurrentGun().getReserveAmmo());
        assertEquals(Game.WIDTH / 2, game.getPlayer().getPosX());
    }

    @Test
    void testKeyPressed() {
        game.keyPressed(KeyEvent.VK_LEFT);
        assertEquals(1, game.getBullets().size());
        game.keyPressed(KeyEvent.VK_RIGHT);
        assertEquals(2, game.getBullets().size());

        game.keyPressed(KeyEvent.VK_R);
        assertEquals(Gun.getMaxClipAmmo(), game.getCurrentGun().getCurrentAmmo());

        game.getCurrentGun().setCurrentAmmo(0);
        game.keyPressed(KeyEvent.VK_RIGHT);
        assertEquals(Gun.getMaxClipAmmo(), game.getCurrentGun().getCurrentAmmo());
        game.getCurrentGun().setCurrentAmmo(1);
        game.getCurrentGun().setReserveAmmo(0);
        game.keyPressed(KeyEvent.VK_RIGHT);
        assertEquals(0, game.getCurrentGun().getCurrentAmmo());

        game.keyPressed(KeyEvent.VK_A);
        assertEquals(Game.WIDTH / 2 - Player.getDX(), game.getPlayer().getPosX());
        game.keyPressed(KeyEvent.VK_D);
        assertEquals(Game.WIDTH/2, game.getPlayer().getPosX());
        game.keyPressed(KeyEvent.VK_J);
    }

    @Test
    void testKeyPressedCantShoot() {
        game.getCurrentGun().setCurrentAmmo(0);
        game.getCurrentGun().setReserveAmmo(0);
        assertEquals(0, game.getBullets().size());
        game.keyPressed(KeyEvent.VK_RIGHT);
        assertEquals(0, game.getBullets().size());
    }

    @Test
    void testUpdateNoCollisions() {
        game.keyPressed(KeyEvent.VK_LEFT);
        assertEquals(1, game.getBullets().size());
        game.update();
        assertEquals(1, game.getBullets().size());
    }

    @Test
    void testUpdateRemoveBulletsOffScreen() {
        game.keyPressed(KeyEvent.VK_LEFT);
        game.keyPressed(KeyEvent.VK_LEFT);
        for (Bullet b: game.getBullets()) {
            b.setPosX(0);
        }
        game.update();
        assertEquals(0, game.getBullets().size());
        game.keyPressed(KeyEvent.VK_RIGHT);
        game.keyPressed(KeyEvent.VK_RIGHT);
        assertEquals(2, game.getBullets().size());
        for (Bullet b: game.getBullets()) {
            b.setPosX(Game.WIDTH);
        }
        game.update();
        assertEquals(0, game.getBullets().size());
        game.update();
    }

    @Test
    void testUpdateGameOver() {
        Zombie initialZombie = game.getZombies().get(0);
        game.getPlayer().setPosX(initialZombie.getPosX());
        game.update();
        game.update();
        assertEquals(0, game.getZombies().size());
        assertEquals(0, game.getBullets().size());
    }

    @Test
    void testUpdateNextRound() {
        game.keyPressed(KeyEvent.VK_RIGHT);
        game.keyPressed(KeyEvent.VK_LEFT);
        Bullet bulletRight = game.getBullets().get(0);
        Bullet bulletLeft = game.getBullets().get(1);
        Zombie initialZombie = game.getZombies().get(0);
        bulletLeft.setPosX(initialZombie.getPosX() + Bullet.DX);
        bulletRight.setPosX(initialZombie.getPosX() - Bullet.DX);
        game.update();
        assertEquals(2, game.getZombies().size());
    }
}
