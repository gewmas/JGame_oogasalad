package gameEngine.view.store;

import gameEngine.controller.Controller;
import gameEngine.model.TowerInfo;
import gameEngine.view.Mediator;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;


/**
 * Panel that contains the different towers a
 * user can select.
 * 
 * @author Lalita Maraj
 * 
 * 
 */
@SuppressWarnings("serial")
public class TowersOptionPanel extends Panel {

    private static final int NUM_COLUMNS = 3;
    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 400;

    /**
     * @param mediator facilitates communication between view components
     * @param controller facilitates communication between view and model
     */
    protected TowersOptionPanel (Mediator mediator, Controller controller) {

        super();
        setUIStyle();
 
        JPanel options = new JPanel();
        GridLayout experimentLayout = new GridLayout(0, NUM_COLUMNS);
        options.setLayout(experimentLayout);
        addStoreInventory(options, mediator, controller);
        JScrollPane scrollPane = new JScrollPane(options);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        add(scrollPane);

    }

    /**
     * Set look and feel of the display of available towers
     */
    private void setUIStyle () {

        Border listPanelBorder =
                BorderFactory.createTitledBorder(StyleConstants.myResources.getString("StoreName"));
        setBorder(listPanelBorder);
    }

    /**
     * Adds buttons based on the defined towers specified by Model
     * 
     * @param optionsPanel panel buttons are added to
     * @param mediator facilitates communication between view components
     * @param controller facilitates communication between view and model
     */
    private void addStoreInventory (JPanel optionsPanel, Mediator mediator, Controller controller) {

        // for (TowerInfo tower: controller.getTowers()){
        // options.add(new TowerStoreButton(tower,mediator,controller));
        // }
        // MOCK DATA
        TowerInfo tw = new TowerInfo("src/resources/right.gif", 45, "fire", "burns things");
        optionsPanel.add(new TowerStoreButton(tw, mediator, controller));
        TowerInfo gw = new TowerInfo("src/resources/mana.jpg", 45, "mana", "burns things");
        optionsPanel.add(new TowerStoreButton(gw, mediator, controller));

    }

}
