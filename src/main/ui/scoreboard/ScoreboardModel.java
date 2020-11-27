package ui.scoreboard;

import model.Record;
import model.Scoreboard;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the scoreboard table and the data it holds
 */
public class ScoreboardModel extends AbstractTableModel {
    protected Scoreboard scoreboard = new Scoreboard();

    // table headings
    String[] columns = new String[]{
            "Name", "Kills", "Points", "Rounds Survived"
    };

    // data for the table in a 2d array
    Object[][] data = new Object[][]{
    };

    // EFFECTS: returns number of columns
    @Override
    public int getRowCount() {
        return data.length;
    }

    // EFFECTS: return name of column col
    public String getColumnName(int col) {
        return columns[col];
    }

    // EFFECTS: returns number of columns
    @Override
    public int getColumnCount() {
        return 4;
    }

    // EFFECTS: sets cell value at given index
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    // EFFECTS: returns cell value at given index
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    // EFFECTS: makes cells non-editable
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    // MODIFIES: this
    // EFFECTS: deletes row at given index and update scoreboard
    public void removeRow(int row) {
        scoreboard.deleteRecord(row);
        List<Object[]> dataList = arrayToArrayList(data);
        dataList.remove(row);
        arrayListToArray(dataList);
        fireTableRowsDeleted(row, row);
    }

    // MODIFIES: this
    // EFFECTS: adds row at the end of the table
    public void addRow(Record r) {
        scoreboard.addRecord(r);
        List<Object[]> dataList = arrayToArrayList(data);
        dataList.add(new Object[]{r.getName(), r.getTotalKills(), r.getTotalPoints(), r.getTotalRounds()});
        arrayListToArray(dataList);
        fireTableRowsInserted(data.length, data.length);

    }

    // MODIFIES: this
    // EFFECTS: converts data  as an ArrayList back into an array
    private void arrayListToArray(List<Object[]> dataList) {
        Object[][] dataArray = new Object[dataList.size()][3];
        data = dataList.toArray(dataArray);
    }

    // EFFECTS: converts data array into an ArrayList
    private List<Object[]> arrayToArrayList(Object[][] data) {
        List<Object[]> dataAsList = new ArrayList<>();
        Collections.addAll(dataAsList, data);
        return dataAsList;
    }

    // MODIFIES: this
    // EFFECTS: updates table to match scoreboard loaded from file
    public void updateScoreboardTable(Scoreboard sb) {
        data = new Object[][]{};
        for (Record r : sb.getScoreboard()) {
            this.addRow(r);
        }
        scoreboard = sb;
    }
}
