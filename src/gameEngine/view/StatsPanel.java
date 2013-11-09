package gameEngine.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;


/**
 * Panel to display game tower statistics
 * 
 * @author Lalita Maraj
 * 
 */
public class StatsPanel extends Panel {
    private JLabel label;

    public StatsPanel () {
        super();
        label = new JLabel("THIS IS AN EXAMPLE");
        Border valuePanelBorder =
                BorderFactory.createTitledBorder(StyleConstants.myResources
                        .getString("TowerStatistics"));
        setBorder(valuePanelBorder);

        add(label);
    }

}
