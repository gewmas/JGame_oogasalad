package gameEngine.view.gameFrame.store;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;
import gameEngine.view.Panel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 * 
 * @author Lalita Maraj
 *         Panel that displays information as key
 *         value pairs in a table
 * 
 * 
 */
public class InfoDisplayPanel extends Panel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int DISPLAY_HEIGHT = 150;
    private static final int DISPLAY_WIDTH = 200;
    private DefaultTableModel model;
    private Font KEYFONT = new Font("Helvetica", 1, 12);
    private Font VALUEFONT = new Font("Helvetica", 0, 12);

    public InfoDisplayPanel (String name) {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(name);
        setBorder(valuePanelBorder);

        initializeContents("", "");

    }

    public InfoDisplayPanel (String name, String keyName, String valueName) {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(name);
        setBorder(valuePanelBorder);

        initializeContents(keyName, valueName);

    }

    /**
     * Initlizes JList contents and adds
     * 
     * @param column2Name name of row
     * @param keyName name of column
     */
    private void initializeContents (String keyName, String valueName) {

        model = new DefaultTableModel(null, new Object[] { keyName, valueName });
        JTable table = new JTable(model);
        setTableFontAndStyle(table);
        JScrollPane listScrollPane = new JScrollPane(table);
        listScrollPane.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        add(listScrollPane);

    }

    private void setTableFontAndStyle (JTable table) {
        // taken from http://stackoverflow.com/questions/16113950/jtable-change-column-font
        table.setFont(VALUEFONT);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent (JTable table,
                                                            Object value,
                                                            boolean isSelected,
                                                            boolean hasFocus,
                                                            int row,
                                                            int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                                                    row, column);
                setFont(KEYFONT);
                this.setForeground(Color.BLUE);
                return this;
            }

        };

        table.getColumnModel().getColumn(0).setCellRenderer(r);
        model.fireTableDataChanged();
    }

    /**
     * A method that is used to update the display's information
     * 
     * @param information information to be displayed
     */
    public void displayInformation (Map<String, String> information) {
        clearDisplay();
        for (String key : information.keySet()) {
            model.addRow(new String[] { key, information.get(key) });
        }
        model.fireTableDataChanged();
    }

    public void clearDisplay () {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
    }
}
