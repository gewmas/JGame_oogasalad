package gameEngine.view.store;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import gameEngine.controller.Controller;
import gameEngine.view.Mediator;
import gameEngine.view.MediatorConstants;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;


/**
 * @author lalitamaraj
 *         Panel to hold the store used to purchase towers
 */
public class TowerStorePanel extends Panel {


    public TowerStorePanel (Mediator mediator, Controller controller) {
        super();

        Panel infoPanel = new InfoPanel();
        Panel options = new TowersOptionPanel(mediator, controller);
        mediator.addColleague(MediatorConstants.INFO_PANEL_KEY, infoPanel);
        
        add(infoPanel, BorderLayout.CENTER);
        add(options, BorderLayout.PAGE_START);
        
        setUIStyle();
    }

    private void setUIStyle () {
        setOpaque(true);
        
       
    }

}
