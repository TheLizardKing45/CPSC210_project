package ui;

import model.Bullet;
import model.Game;
import model.Player;
import model.Zombie;

import javax.swing.*;
import java.awt.*;

/**
 * Renders the game
 * Learned how to paint components using https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
 */
public class GamePanel extends JPanel {
    private Game game;

    // EFFECTS: constructs game panel. sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(Game game) {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setBackground(Color.GRAY);
        this.game = game;
    }


    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: this
    // EFFECTS: called when components need to be drawn
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    // EFFECTS: draws the game
    // MODIFIES: g
    private void drawGame(Graphics g) {
        drawPlayer(g);
        drawBullets(g);
        drawZombies(g);
        if (game.getIsOver()) {
            g.setColor(new Color(160, 0, 0));
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        }
    }

    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: g
    // EFFECTS: draws zombies
    private void drawZombies(Graphics g) {
        for (Zombie z : game.getZombies()) {
            drawZombie(z, g);
        }
    }

    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: g
    // EFFECTS: draws zombie
    private void drawZombie(Zombie z, Graphics g) {
        g.setColor(Zombie.COLOR);
        g.fillRect(z.getPosX()
                - Zombie.SIZE_X / 2, Zombie.Y_POS - Zombie.SIZE_Y / 2, Zombie.SIZE_X, Zombie.SIZE_Y);
    }

    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: g
    // EFFECTS: draw tank
    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();
        g.setColor(Player.COLOR);
        g.fillRect(p.getPosX() - Player.SIZE_X / 2, Player.Y_POS
                - Player.SIZE_Y / 2, Player.SIZE_X, Player.SIZE_Y);
    }

    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: g
    // EFFECTS: draws bullets
    private void drawBullets(Graphics g) {
        for (Bullet b : game.getBullets()) {
            drawBullet(g, b);
        }
    }

    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: g
    // EFFECTS: draws bullet b onto g
    private void drawBullet(Graphics g, Bullet b) {
        g.setColor(Bullet.COLOR);
        g.fillRect(b.getPosX() - Bullet.SIZE_X, Bullet.Y_POS - Bullet.SIZE_Y, Bullet.SIZE_X, Bullet.SIZE_Y);
    }
}
