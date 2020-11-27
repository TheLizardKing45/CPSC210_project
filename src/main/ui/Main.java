package ui;

import model.Game;
import model.Record;
import ui.scoreboard.ScorePanel;
import ui.scoreboard.ScoreboardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *  runs the game
 */

public class Main extends JFrame {
    private static final int INTERVAL = 20;
    // music from https://www.youtube.com/watch?v=Ays8bLTcpxc&list=PLcbeqnuEQj68wcCOAiRaVQiEd7uH2HwJo&index=14
    private static final String MUSIC_PATH = "./data/music.wav";
    private Game game;
    private ScorePanel scorePanel;
    private GamePanel gamePanel;
    private Timer timer;
    private GameOverDialog gameOverDialog;
    private boolean paused;

    // EFFECTS: constructs the main window where the game will be played
    public Main() {
        game = new Game();
        gamePanel = new GamePanel(game);
        scorePanel = new ScorePanel(game);
        this.setTitle("Apocalypse");
        this.add(gamePanel);
        this.add(scorePanel, BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        this.addKeyListener(new KeyHandler());
        this.addTimer();
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        playMusic(MUSIC_PATH);
    }

    // EFFECTS: plays gun sound effect
    private void playMusic(String path) {
        SoundPlayer.playMusic(path);
    }

    // MODIFIES: this
    // EFFECTS: opens game over window that allows user to input their name if the game is over, restarts game
    //          and saves record to scoreboard
    private void handleGameOver() {
        if (game.getIsOver()) {
            gameOverDialog = new GameOverDialog();
            gameOverDialog.setVisible(true);
            saveToScoreboard();
            game.setUp();
            pause();
        }
    }

    // MODIFIES: this
    // EFFECTS: saves game statistics to scoreboard
    private void saveToScoreboard() {
        if (gameOverDialog.getPlayerName() != null) {
            ScoreboardModel scoreboard = scorePanel.getScoreboardWindow().getModel();
            scoreboard.addRow(new Record(gameOverDialog.getPlayerName(), game.getKills(),
                    game.getPoints(), game.getCurrentRound()));
        }
    }

    // EFFECTS: runs the game
    public static void main(String[] args) {
        new Main();
    }

    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: this
    // EFFECTS: receives keyboard input
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!paused) {
                game.keyPressed(e.getKeyCode());
            }
            handlePause(e.getKeyCode());
        }
    }

    // MODIFIES: this
    // EFFECTS: pauses or unpauses the game
    private void handlePause(int keyCode) {
        if (keyCode == KeyEvent.VK_P) {
            if (paused) {
                unpause();
            } else {
                pause();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: unpauses the game
    private void unpause() {
        timer.restart();
        paused = false;
    }

    // MODIFIES: this
    // EFFECTS: pauses the game
    private void pause() {
        timer.stop();
        paused = true;
    }

    // MODIFIES: this
    // EFFECTS: pauses the game when the game is not in focus
    private void pauseOnUnfocus() {
        if (!this.hasFocus()) {
            pause();
        }
    }


    // from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    // MODIFIES: this
    // EFFECTS:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        timer = new Timer(INTERVAL, ae -> {
            pauseOnUnfocus();
            handleGameOver();
            game.update();
            gamePanel.repaint();
            scorePanel.update();
        });

        timer.start();
    }
}
