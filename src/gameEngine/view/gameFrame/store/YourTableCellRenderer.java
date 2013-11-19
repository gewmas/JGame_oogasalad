package gameEngine.view.gameFrame.store;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;


public class YourTableCellRenderer
        extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent (JTable table,
                                                    Object value,
                                                    boolean isSelected,
                                                    boolean hasFocus,
                                                    int row,
                                                    int column) {
        Component c =
                super.getTableCellRendererComponent(table, value,
                                                    isSelected, hasFocus,
                                                    row, column);

        // Only for specific cell
        if (column == 1) {
            c.setFont(new java.awt.Font("Arial Unicode MS", 1, 20));
            // you may want to address isSelected here too
            // c.setForeground(/*special foreground color*/);
            // c.setBackground(/*special background color*/);
        }
        return c;
    }
}
