package gameEngine.view.gameFrame;

import gameEngine.view.StyleConstants;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 * Panel to display game tower statistics
 * 
 * @author Lalita Maraj
 * 
 */
public class StatsPanel extends JPanel {
    private JLabel label;

    public StatsPanel () {
        super();
        label = new JLabel("THIS IS AN EXAMPLE");
        Border valuePanelBorder =
                BorderFactory.createTitledBorder(StyleConstants.resourceBundle
                        .getString("TowerStatistics"));
        setBorder(valuePanelBorder);

        add(label);
    }

}
