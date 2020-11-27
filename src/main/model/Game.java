package model;


import ui.SoundPlayer;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game
 */
public class Game {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    // sound from https://freesound.org/people/djgriffin/sounds/21999/
    private static final String GUN_SOUND_PATH = "./data/gunshot.wav";
    private static final int POINTS_PER_KILL = 10;
    private List<Zombie> zombies;
    private List<Bullet> bullets;
    private Player player;
    private Gun currentGun;
    private int currentRound;
    private int points;
    private int kills;
    private boolean isOver;

    // organized similarly to SIGame constructor in https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // EFFECTS: creates the game with initial set up
    public Game() {
        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
        setUp();
    }


    // similar to https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: this
    // EFFECTS: sets up/ resets the game
    public void setUp() {
        zombies.clear();
        bullets.clear();
        zombies.add(new Zombie());
        currentRound = 1;
        points = 0;
        kills = 0;
        isOver = false;
        currentGun = new Gun();
        player = new Player();
    }


    // similar to https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // Responds to key press codes
    // MODIFIES: this
    // EFFECTS:  move player, shoot and resets game in response to
    //           given key pressed code
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            shootGun(keyCode);
        } else if (keyCode == KeyEvent.VK_R) {
            currentGun.reload();
        } else if (keyCode == KeyEvent.VK_A) {
            player.move(-1);
        } else if (keyCode == KeyEvent.VK_D) {
            player.move(1);
        }
    }

    // MODIFIES: this
    // EFFECTS: shoots gun in either the left or right direction (creates a bullet)
    private void shootGun(int keyCode) {
        if (currentGun.getReserveAmmo() != 0 || currentGun.getCurrentAmmo() != 0) {
            Bullet bullet;
            currentGun.shoot();
            if (keyCode == KeyEvent.VK_LEFT) {
                bullet = new Bullet(player.getPosX(), -1);
            } else {
                bullet = new Bullet(player.getPosX(), 1);
            }
            playSound(GUN_SOUND_PATH);
            bullets.add(bullet);
        }
    }

    // EFFECTS: plays gun sound effect
    private void playSound(String path) {
        SoundPlayer.playSound(path);
    }


    // MODIFIES: this
    // EFFECTS: updates zombies, bullets, and player
    public void update() {
        moveZombies();
        moveBullets();
        checkBullets();
        checkCollisions();
        handleRoundOver();
        checkGameOver();
    }

    // MODIFIES: this
    // EFFECTS: if round is over, adds more zombies to game and increase round number
    private void handleRoundOver() {
        if (zombies.size() == 0 && !isOver) {
            currentRound++;
            for (int i = 0; i < currentRound; i++) {
                zombies.add(new Zombie());
            }
        }
    }

    // similar to https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES THIS:
    // EFFECTS:  game is over if a zombie has touched the player
    private void checkGameOver() {
        for (Zombie z : zombies) {
            if (player.collidedWith(z)) {
                isOver = true;
            }
        }
        if (isOver) {
            zombies.clear();
            bullets.clear();
        }
    }

    // similar to https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: this
    // EFFECTS: checks and removes any bullet that hit a zombie as well as the zombie that was hit
    private void checkCollisions() {
        List<Zombie> zombiesToRemove = new ArrayList<>();
        List<Bullet> bulletsToRemove = new ArrayList<>();

        for (Zombie target : zombies) {
            if (checkZombieHit(target, bulletsToRemove)) {
                zombiesToRemove.add(target);
            }
        }

        zombies.removeAll(zombiesToRemove);
        bullets.removeAll(bulletsToRemove);
    }

    // similar to https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: this, bulletsToRemove
    // EFFECTS: returns true if zombie was hit by a bullet
    private boolean checkZombieHit(Zombie z, List<Bullet> bulletsToRemove) {
        for (Bullet next : bullets) {
            if (z.collidedWith(next)) {
                bulletsToRemove.add(next);
                points += POINTS_PER_KILL;
                kills++;
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes bullets that travelled offscreen
    private void checkBullets() {
        bullets.removeIf(b -> b.getPosX() < 0 || b.getPosX() > WIDTH);
    }

    // MODIFIES: this
    // EFFECTS: moves all bullets in their respective directions
    private void moveBullets() {
        for (Bullet b : bullets) {
            b.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: moves all zombies currently on screen towards the player
    private void moveZombies() {
        for (Zombie zb : zombies) {
            zb.move(zombieDistance(zb));
        }
    }

    // EFFECTS: return the distance between the player and a zombie
    private int zombieDistance(Zombie zb) {
        return (player.getPosX() - zb.getPosX());
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int round) {
        this.currentRound = round;
    }

    public int getPoints() {
        return points;
    }

    public int getKills() {
        return kills;
    }

    public Gun getCurrentGun() {
        return currentGun;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public boolean getIsOver() {
        return isOver;
    }

    public Player getPlayer() {
        return player;
    }

}

