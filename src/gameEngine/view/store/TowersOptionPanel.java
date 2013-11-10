package gameEngine.view.store;

import gameEngine.model.TowerInfo;
import gameEngine.view.GameFrame;
import gameEngine.view.Mediator;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import java.awt.Dimension;
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

    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 400;

    /**
     * @param mediator facilitates communication between view components
     * @param gameFrame facilitates communication between view and controller
     */
    protected TowersOptionPanel (Mediator mediator, GameFrame gameFrame) {

        super();
        setUIStyle();

        JPanel options = new JPanel();
        options.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        addStoreInventory(options, mediator, gameFrame);

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
                BorderFactory.createTitledBorder(StyleConstants.resourceBundle
                        .getString("StoreName"));
        setBorder(listPanelBorder);
    }

    /**
     * Adds buttons based on the defined towers specified by Model
     * 
     * @param optionsPanel panel buttons are added to
     * @param mediator facilitates communication between view components
     * @param gameFrame facilitates communication between view and model
     */
    private void addStoreInventory (JPanel optionsPanel, Mediator mediator, GameFrame gameFrame) {

        // for (TowerInfo tower: gameFrame.getTowers()){
        // options.add(new TowerStoreButton(tower,mediator,gameFrame));
        // }
        // MOCK DATA
        TowerInfo tw = new TowerInfo("src/resources/right.gif", 45, "fire", "burns enemies");
        optionsPanel.add(new TowerStoreButton(tw, mediator, gameFrame));
        TowerInfo gw = new TowerInfo("src/resources/mana.jpg", 45, "ice", "freeze enemies");
        optionsPanel.add(new TowerStoreButton(gw, mediator, gameFrame));
        optionsPanel.add(new TowerStoreButton(gw, mediator, gameFrame));
        optionsPanel.add(new TowerStoreButton(gw, mediator, gameFrame));
        optionsPanel.add(new TowerStoreButton(gw, mediator, gameFrame));
        optionsPanel.add(new TowerStoreButton(gw, mediator, gameFrame));
        optionsPanel.add(new TowerStoreButton(gw, mediator, gameFrame));
        optionsPanel.add(new TowerStoreButton(gw, mediator, gameFrame));
        optionsPanel.add(new TowerStoreButton(gw, mediator, gameFrame));
        // END MOCK DATA

    }

}
