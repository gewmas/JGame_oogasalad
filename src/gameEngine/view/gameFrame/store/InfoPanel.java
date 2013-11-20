package gameEngine.view.gameFrame.store;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 * Panel that displays tower informations
 * when user hovers over a tower option
 * 
 * @author Lalita Maraj
 * 
 */
public class InfoPanel extends Panel {

    private DefaultTableModel model;
    private Font KEYFONT = new Font("Helvetica", 1, 12);
    private Font VALUEFONT = new Font("Helvetica", 0, 12);

    public InfoPanel (String name) {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(name);
        setBorder(valuePanelBorder);

        initializeContents();

    }

    /**
     * Initlizes JList contents and adds
     */
    private void initializeContents () {

        model =
                new DefaultTableModel(null,
                                      new Object[] { "", "" });
        JTable table = new JTable(model);

        table.setFont(VALUEFONT);

        // taken from http://stackoverflow.com/questions/16113950/jtable-change-column-font
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {

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
        JScrollPane listScrollPane = new JScrollPane(table);
        listScrollPane.setPreferredSize(new Dimension(200, 150));
        add(listScrollPane);

    }

    /**
     * A method that is called by the Mediator to
     * update the information this panel displays
     * 
     * @param towerDisplayInfo TODO
     */
    public void displayTowerInfo (Map<String, String> towerDisplayInfo) {
        clearDisplay();
        for (String key : towerDisplayInfo.keySet()) {
            model.addRow(new String[] { key, towerDisplayInfo.get(key) });
        }
        model.fireTableDataChanged();
    }

    public void clearDisplay () {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
    }
}
