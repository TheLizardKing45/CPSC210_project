package ui.scoreboard;


import model.Record;
import model.Scoreboard;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * represents screen with scoreboard with ability to delete, save or load scoreboard
 */
public class ScoreboardUI extends JPanel {
    private JButton saveButton;
    private JButton loadButton;
    private JButton deleteButton;
    private JButton addButton;
    private static final String JSON_STORE = "./data/scoreboard.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private JTable table;
    private ScoreboardModel model;
    private TitledBorder title;
    private static final String SAVE_TEXT = "Save";
    private static final String LOAD_TEXT = "Load";
    private static final String DELETE_TEXT = "Delete Record";

    // EFFECTS: constructs the scoreboard panel
    public ScoreboardUI() {
        //create table with data using scoreboard model
        model = new ScoreboardModel();
        table = new JTable(model);

        handleButtons();
        TitledBorder title = createBorder();
        //add the table to the frame in a scroll
        this.add(new JScrollPane(table));
        this.setBorder(title);
        // add buttons
        this.add(deleteButton);
        this.add(saveButton);
        this.add(loadButton);
        //this.add(addButton); only used for debugging
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create titled border displaying Scoreboard as the title
    private TitledBorder createBorder() {
        title = BorderFactory.createTitledBorder("Scoreboard");
        title.setTitleJustification(TitledBorder.CENTER);
        return title;
    }

    public ScoreboardModel getTableModel() {
        return model;
    }

    // MODIFIES: this
    // EFFECTS: creates buttons and their functionalities
    private void handleButtons() {
        createSaveButton();
        createLoadButton();
        createDeleteButton();
        //createAddButton(); only for debugging
    }

    // MODIFIES: this
    // EFFECTS: adds creates add record button (only used for debugging)
    private void createAddButton() {
        addButton = new JButton("Add Record (debug)");
        addButton.addActionListener(e -> {
            model.addRow(new Record("Brett", 50, 40, 60));
            System.out.println(model.scoreboard.scoreboardSize());
        });
    }

    // MODIFIES: this
    // EFFECTS: creates load button
    private void createLoadButton() {
        loadButton = new JButton(LOAD_TEXT);
        loadButton.addActionListener(e -> {
            try {
                Scoreboard scoreboardFromFile = jsonReader.read();
                System.out.println("Loaded scoreboard from " + JSON_STORE);
                model.updateScoreboardTable(scoreboardFromFile);
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates save button
    public void createSaveButton() {
        saveButton = new JButton(SAVE_TEXT);
        saveButton.addActionListener(e -> {
            try {
                jsonWriter.open();
                jsonWriter.write(model.scoreboard);
                jsonWriter.close();
                System.out.println("Saved scoreboard to " + JSON_STORE);
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates Delete button
    private void createDeleteButton() {
        deleteButton = new JButton(DELETE_TEXT);
        deleteButton.addActionListener(e -> {
            if (table.getSelectedRow() != -1) {
                model.removeRow(table.getSelectedRow());
            }
        });
        deleteButton.setEnabled(false);

        // greys out delete button when no row is selected
        // from https://stackoverflow.com/questions/4399975/jtable-no-selected-row
        ListSelectionModel listSelectionModel = table.getSelectionModel();
        listSelectionModel.addListSelectionListener(e -> {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            deleteButton.setEnabled(!lsm.isSelectionEmpty());
        });
    }
}
