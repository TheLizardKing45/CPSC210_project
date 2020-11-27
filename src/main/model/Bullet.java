package model;

import java.awt.*;

/**
 * Represents a bullet
 */
public class Bullet implements Entity {

    public static final int SIZE_X = 9;
    public static final int SIZE_Y = 5;
    private int posX;

    private int direction; // either -1 or 1
    public static final int DX = 8;
    public static final int Y_POS = Game.HEIGHT / 2;
    public static final Color COLOR = new Color(128, 50, 20);


    // Constructs a bullet
    // EFFECTS: bullet is positioned at position x with the direction in which it was shot
    public Bullet(int x, int direction) {
        this.posX = x;
        this.direction = direction;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }


    // MODIFIES: this
    // EFFECT: moves bullet
    public void move() {
        posX = posX + (direction * DX);
    }


}
