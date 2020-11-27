package ui.scoreboard;

import javax.swing.*;
import java.awt.*;

/**
 * window that holds the scoreboard
 */
public class ScoreboardWindow extends JDialog {
    ScoreboardUI ui;

    // EFFECTS: constructs the scoreboard window that can be opened in game
    public ScoreboardWindow() {
        ui = new ScoreboardUI();
        this.add(ui);
        this.setResizable(false);
        this.setTitle("Scoreboard");
        this.setPreferredSize(new Dimension(550, 800));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public ScoreboardModel getModel() {
        return ui.getTableModel();
    }
}
