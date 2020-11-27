package ui.scoreboard;

import model.Game;
import model.Gun;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the panel that shows the current game's score and other statistics
 * Modeled after https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
 */
public class ScorePanel extends JPanel {
    private static final String ZOMBIE_TEXT = "Zombies left: ";
    private static final String ROUNDS_TEXT = "Rounds: ";
    private static final String KILLS_TEXT = "Kills: ";
    private static final String GUN_TEXT = "Gun: ";
    private static final String POINTS_TEXT = "Points: ";
    private static final int LBL_WIDTH = 150;
    private static final int LBL_HEIGHT = 30;
    private JLabel zombieLabel;
    private JLabel roundsLabel;
    private JLabel killsLabel;
    private JLabel gunLabel;
    private JLabel pointsLabel;
    private ScoreboardWindow scoreboardWindow;
    private Game game;
    private static final String SCOREBOARD_BUTTON_TEXT = "Scoreboard";
    private JButton scoreboardButton;

    // EFFECTS: constructs the score panel showing game statistics
    public ScorePanel(Game game) {
        this.game = game;
        setBackground(new Color(180, 180, 180));
        createScoreboardButton();
        drawLabels(game);
        this.add(scoreboardButton, BorderLayout.SOUTH);
        scoreboardButton.setFocusable(false);
        this.setFocusable(false);
        this.add(zombieLabel);
        this.add(roundsLabel);
        this.add(killsLabel);
        this.add(gunLabel);
        this.add(pointsLabel);
    }


    public ScoreboardWindow getScoreboardWindow() {
        return scoreboardWindow;
    }

    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: this
    // EFFECTS:  updates the statistics on the scoreboard panel
    public void update() {
        zombieLabel.setText(ZOMBIE_TEXT + game.getZombies().size());
        roundsLabel.setText(ROUNDS_TEXT + game.getCurrentRound());
        killsLabel.setText(KILLS_TEXT + game.getKills());
        Gun gun = game.getCurrentGun();
        gunLabel.setText(GUN_TEXT + gun.getCurrentAmmo() + "/" + gun.getReserveAmmo());
        pointsLabel.setText(POINTS_TEXT + game.getPoints());
        repaint();
    }

    // MODIFIES: this
    // EFFECT: creates the Scoreboard button that displays the scoreboard
    private void createScoreboardButton() {
        scoreboardWindow = new ScoreboardWindow();
        scoreboardButton = new JButton(SCOREBOARD_BUTTON_TEXT);
        scoreboardButton.addActionListener(e -> {
            scoreboardWindow.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            scoreboardWindow.setVisible(true);
        });
    }

    // MODIFIES: this
    // EFFECTS: creates the various labels on the scoreboard
    private void drawLabels(Game game) {
        zombieLabel = new JLabel(ZOMBIE_TEXT + game.getZombies().size());
        zombieLabel.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        roundsLabel = new JLabel(ROUNDS_TEXT + game.getCurrentRound());
        roundsLabel.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        killsLabel = new JLabel(KILLS_TEXT + game.getKills());
        killsLabel.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        Gun gun = game.getCurrentGun();
        gunLabel = new JLabel(GUN_TEXT + gun.getCurrentAmmo() + "/" + gun.getReserveAmmo());
        gunLabel.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        pointsLabel = new JLabel(POINTS_TEXT + game.getPoints());
        pointsLabel.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
    }
}
