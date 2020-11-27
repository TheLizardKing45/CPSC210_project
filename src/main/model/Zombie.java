package model;


import java.awt.*;
import java.util.Random;

/**
 * Represents a zombie that tries to kill the player
 */

public class Zombie implements LivingEntity {

    private int dx;
    private int posX;
    public static final int Y_POS = Game.HEIGHT / 2;
    public static final int MAX_SPEED = 8;
    public static final int MIN_SPEED = 3;
    public static final int SIZE_X = 9;
    public static final int SIZE_Y = 15;
    public static final Color COLOR = new Color(50, 190, 150);

    // EFFECTS: creates a zombie on either side of the game boundary with a speed between a certain range
    public Zombie() {
        Random randomSpeed = new Random();
        dx = MIN_SPEED + randomSpeed.nextInt(MAX_SPEED - MIN_SPEED + 1);
        posX = choosePosition(Game.WIDTH);

    }

    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // EFFECTS: returns true if a bullet has collided with a zombie
    @Override
    public boolean collidedWith(Entity e) {
        Rectangle zombieBoundingRect = new Rectangle(getPosX() - SIZE_X / 2, Y_POS - SIZE_Y / 2, SIZE_X, SIZE_Y);
        Rectangle bulletBoundingRect = new Rectangle(e.getPosX() - Bullet.SIZE_X / 2, Bullet.Y_POS - Bullet.SIZE_Y / 2,
                Bullet.SIZE_X, Bullet.SIZE_Y);
        return zombieBoundingRect.intersects(bulletBoundingRect);
    }

    public int getDX() {
        return this.dx;
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    // MODIFIES: this
    // EFFECTS: randomly picks between the left or right boundary of the game area to place the zombie and returns
    // chosen position
    private int choosePosition(int gameWidth) {
        boolean rng = new Random().nextBoolean();
        int position;
        if (rng) {
            position = gameWidth;
        } else {
            position = 0;
        }
        return position;

    }

    // MODIFIES: this
    // EFFECTS: moves zombie DX units towards player depending on whether they are to the left or right of the player
    @Override
    public void move(int diff) {            // move will take the distance between the player and a zombie
        if (diff > 0) {                     // to the left of player
            posX += dx;
        } else if (diff < 0) {              // to the right of player
            posX -= dx;
        }
    }
}
