package gameEngine.view.store;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import gameEngine.view.Panel;


/**
 * @author lalitamaraj
 *         Panel to hold the store used to purchase towers
 */
public class TowerStorePanel extends Panel {
    public TowerStorePanel () {
        super();
        ImageIcon icon = new ImageIcon("src/resources/right.gif");
        InfoPanel infoPanel = new InfoPanel();
        setOpaque(true);
        TowersOptionPanel options = new TowersOptionPanel("test", infoPanel);
        JScrollPane sp =
                new JScrollPane(options, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        sp.setPreferredSize(new Dimension(300, 100));

        add(sp, BorderLayout.PAGE_START);

        add(infoPanel, BorderLayout.CENTER);

    }

}
