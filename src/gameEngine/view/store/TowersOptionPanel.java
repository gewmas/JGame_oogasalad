package gameEngine.view.store;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * 
 * Acts as a container that displays the different types of user input.
 * Each element of displayed information is clickable and copy/paste enabled
 * This class gathers the information to be displayed from the Model and displays it
 * using a JList
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
@SuppressWarnings("serial")
public class TowersOptionPanel extends JPanel {



    protected TowersOptionPanel (String name, InfoPanel infoPanel) {

        super();

     
        GridLayout experimentLayout = new GridLayout(0, 3);

        setLayout(experimentLayout);

        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));
        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));
        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));
        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));
        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));
        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));
        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));
        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));
        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));
        add(new TowerStoreButton(name, infoPanel, null, "cost", "desc", "power"));

        Border listPanelBorder =
                BorderFactory.createTitledBorder("Store");
        setBorder(listPanelBorder);

    }
    
    

}
