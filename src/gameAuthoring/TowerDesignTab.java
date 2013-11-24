package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;


/**
 * @author BecLai
 * 
 */
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
        title.setFont(new Font("Calibri", Font.PLAIN, 30));
        title.setForeground(new Color(80, 80, 80));
        mainPanel.add(title, "span 2");
        mainPanel.setPreferredSize(new Dimension(500, 500));
        myScrollPanel = new JPanel(new MigLayout("wrap 8"));
        TowerDesignPanel towerDesignDialog = new TowerDesignPanel(this);
        mainPanel.add(towerDesignDialog, "span 2");
        myCreatedTowers = new JScrollPane(myScrollPanel);
        myCreatedTowers.setPreferredSize(new Dimension(380, 200));
        Border b = BorderFactory.createLoweredBevelBorder();
        myCreatedTowers.setBorder(BorderFactory
                .createTitledBorder(b, "Created Towers",
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    new Font("Arial", Font.BOLD, 16)));
        mainPanel.add(myCreatedTowers);
        return mainPanel;
    }

    public void addTower (File imgSource, String towerName) {
        JLabel towerIcon = new JLabel();
        try {
            Image towerImage = ImageIO.read(imgSource);
            towerIcon.setIcon(new ImageIcon(towerImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JLabel towerNameLabel = new JLabel(towerName);
        myScrollPanel.add(towerNameLabel);
        myScrollPanel.add(towerIcon);

    }

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub

    }
}
