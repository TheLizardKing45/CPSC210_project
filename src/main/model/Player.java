package model;


import java.awt.*;

/**
 * Represents the controllable player
 */

public class Player implements LivingEntity {

    private int posX;
    private static final int DX = 10;
    public static final int SIZE_X = 9;
    public static final int SIZE_Y = 15;
    public static final int Y_POS = Game.HEIGHT / 2;
    public static final Color COLOR = new Color(200, 100, 0);

    // EFFECTS: constructs a new player
    public Player() {
        this.posX = (Game.WIDTH / 2);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public static int getDX() {
        return DX;
    }


    // MODIFIES: this
    // EFFECTS: moves the player in direction passed in (either left or right) by DX units
    // if they are within the game boundary, otherwise do not move
    @Override
    public void move(int dir) {
        if (dir == -1) {
            posX -= DX;
            handleBoundary();
        } else if (dir == 1) {
            posX += DX;
            handleBoundary();
        }
    }

    // MODIFIES: this
    // EFFECTS: keeps player within the boundary of the game area
    private void handleBoundary() {
        if (posX < 0) {
            posX = 0;
        } else if (posX > Game.WIDTH) {
            posX = Game.WIDTH;
        }
    }

    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // EFFECTS: returns true if player has collided with a zombie
    @Override
    public boolean collidedWith(Entity e) {
        Rectangle playerBoundingRect = new Rectangle(getPosX() - SIZE_X / 2, Y_POS - SIZE_Y / 2, SIZE_X, SIZE_Y);
        Rectangle zombBoundingRect = new Rectangle(e.getPosX()
                - Zombie.SIZE_X / 2, Zombie.Y_POS - Zombie.SIZE_Y / 2,
                Zombie.SIZE_X, Zombie.SIZE_Y);
        return zombBoundingRect.intersects(playerBoundingRect);
    }
}
