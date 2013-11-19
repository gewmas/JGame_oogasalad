package gameEngine.view.gameFrame.store;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;


public class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    JComponent component = new JTextField();

    public Component getTableCellEditorComponent (JTable table,
                                                  Object value,
                                                  boolean isSelected,
                                                  int rowIndex,
                                                  int vColIndex) {
        ((JTextField) component).setText((String) value);
        ((JTextField) component).setFont(new java.awt.Font("Arial Unicode MS", 1, 20));
        return component;
    }

    @Override
    public Object getCellEditorValue () {
        // TODO Auto-generated method stub
        return null;
    }
}
