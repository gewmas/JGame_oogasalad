package gameEngine.view.gameFrame.store;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;


public class UpgradeTable extends AbstractTableModel {

    Vector textData = new Vector();

    public void addText (String text) {
        // textData.clear();
        textData.addElement(text);
        fireTableDataChanged();
    }

    public int getRowCount () {
        return textData.size();
    }

    public int getColumnCount () {
        return 2;
    }

    public Object getValueAt (int row, int column) {
        return textData.elementAt(row);
    }

}
