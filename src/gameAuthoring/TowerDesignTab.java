package gameAuthoring;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class TowerDesignTab extends Tab {

    private JScrollPane myCreatedTowers;
    private JPanel myScrollPanel;

    public TowerDesignTab (GameData gameData) {
        super(gameData);
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2"));
        JLabel title = new JLabel("Tower Design");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(title, "span 2");
        mainPanel.setPreferredSize(new Dimension(500, 500));
        myScrollPanel = new JPanel(new MigLayout("wrap 2, align center"));
        TowerDesignPanel towerDesignDialog = new TowerDesignPanel(this);
        mainPanel.add(towerDesignDialog);
        myCreatedTowers = new JScrollPane(myScrollPanel);
        myCreatedTowers.setPreferredSize(new Dimension(200, 400));
        mainPanel.add(myCreatedTowers, "pad 30 60 10 40");
        return mainPanel;
    }

    public void addTower (File imgSource, String towerName) {
        JLabel towerIcon = new JLabel();
        try {
            Image towerImage = ImageIO.read(imgSource);
            towerIcon.setIcon(new ImageIcon(towerImage));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JLabel towerNameLabel = new JLabel(towerName);
        myScrollPanel.add(towerNameLabel);
        myScrollPanel.add(towerIcon);

    }
}
