package gameEngine.view.store;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
        add(options, BorderLayout.PAGE_START);

        add(infoPanel, BorderLayout.CENTER);

    }

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        frame.add(new TowerStorePanel());
        frame.pack();
        frame.setVisible(true);
    }
}
