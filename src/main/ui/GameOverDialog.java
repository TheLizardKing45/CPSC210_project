package ui;

import javax.swing.*;

/**
 * Dialog box that shows up when the game is over and allows user to type in their name
 */
public class GameOverDialog extends JOptionPane {
    private boolean closeDialog = false;
    private String name = "";

    // EFFECTS: constructs the game over dialog window
    public GameOverDialog() {
        while (!closeDialog) {
            name = JOptionPane.showInputDialog(null,
                    "Please type in your name: (or press cancel to not save your game score) ",
                    "Game Over!", PLAIN_MESSAGE);
            if (name == null) {
                showMessageDialog(null, "Current game statistics not saved.");
                closeDialog = true;
            } else if (name.length() > 0) {
                closeDialog = true;
            }
        }
    }

    public String getPlayerName() {
        return name;
    }
}
