package gameAuthoring;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


public class MiscellaneousTab extends Tab {

    // bullet image
    // magic image for each
    // speed
    // flame
    // invisibility - enemy
    // poison -
    // damage
    public MiscellaneousTab (GameData gameData) {
        super(gameData);
    }

    @Override
    public JPanel getTab () {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 1"));
        JLabel title = new JLabel("Miscellaneous");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(title, "span 2");
        mainPanel.setPreferredSize(new Dimension(500, 500));

        JLabel speedLabel = new JLabel("Select image for slow down buff");
        JLabel poisonLabel = new JLabel("Select image for poison buff");
        JLabel flameLabel = new JLabel("Select image for flame buff");

        mainPanel.add(speedLabel);
        mainPanel.add(poisonLabel);
        mainPanel.add(flameLabel);

        return mainPanel;
    }

}
