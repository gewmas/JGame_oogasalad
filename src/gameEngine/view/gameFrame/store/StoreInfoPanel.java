package gameEngine.view.gameFrame.store;

import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


/**
 * Panel that displays tower informations
 * when user hovers over a tower option
 * 
 * @author Lalita Maraj
 * 
 */
public class StoreInfoPanel extends Panel {

    private JTextPane text;
    private DefaultTableModel model;

    public StoreInfoPanel () {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(StyleConstants.resourceBundle
                        .getString("TowerInfo"));
        setBorder(valuePanelBorder);

        initializeContents();

    }

    /**
     * Initlizes JList contents and adds
     */
    private void initializeContents () {

        text = new JTextPane();

        text.setPreferredSize(new Dimension(300, 100));
        text.setContentType("text/html");
        model =
                new DefaultTableModel(null,
                                      new Object[] {
                                                    StyleConstants.resourceBundle
                                                            .getString("Attribute"),
                                                    StyleConstants.resourceBundle
                                                            .getString("Value") });
        JTable table = new JTable(model);
        JScrollPane listScrollPane = new JScrollPane(table);
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
