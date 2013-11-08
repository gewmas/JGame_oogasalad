package gameEngine.view.store;

import gameEngine.controller.Controller;
import gameEngine.model.TowerInfo;
import gameEngine.view.Mediator;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;


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
public class TowersOptionPanel extends Panel {

    private static final int NUM_COLUMNS = 3;
    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 400;

    protected TowersOptionPanel (

                                 Mediator mediator,
                                 Controller controller) {

        super();
        setUIStyle();

        JPanel options = new JPanel();
        GridLayout experimentLayout = new GridLayout(0, NUM_COLUMNS);
        options.setLayout(experimentLayout);
        addStoreInventory(options, mediator, controller);
        JScrollPane sp =
                new JScrollPane(options, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        sp.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        add(sp);
        
    }

    private void setUIStyle () {

        Border listPanelBorder =
                BorderFactory.createTitledBorder(StyleConstants.myResources.getString("StoreName"));
        setBorder(listPanelBorder);
    }

    private void addStoreInventory (JPanel options, Mediator mediator, Controller controller) {

        // for (TowerInfo tower: controller.getTowers()){
        // options.add(new TowerStoreButton(tower,mediator,controller));
        // }
        // MOCK DATA
        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));
        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));

        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));
        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));
        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));
        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));

        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));
        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));
        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));
        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));

        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                 controller));
        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                 controller));
        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                 mediator, controller));
        options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
                options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
                options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));
                options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                                 controller));
                options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                                 controller));
                options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                                 mediator, controller));
                options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                                 controller));
                options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                                 controller));
                options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                                 mediator, controller));

                options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                         controller));
                options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                         controller));
                options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                         mediator, controller));
                options.add(new TowerStoreButton("ice", null, 45, "desc", "power", mediator,
                                                 controller));
                        options.add(new TowerStoreButton("monkey", null, 2, "desc", "power", mediator,
                                                 controller));
                        options.add(new TowerStoreButton("fire", null, 22, "desc", "power",
                                                 mediator, controller));



        // MOCK DATA

    }

}
