package gameAuthoring.view;

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

    public TowerDesignTab () {
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel mainPanel = new GradientPanel(new MigLayout("wrap 2"));
        JLabel title = new JLabel("Tower Design");
        title.setFont(new Font("Calibri", Font.PLAIN, 30));
        title.setForeground(new Color(80, 80, 80));
        mainPanel.add(title, "span 2");
        mainPanel.setPreferredSize(new Dimension(500, 500));
        myScrollPanel = new JPanel(new MigLayout("wrap 8"));
        myScrollPanel.setOpaque(false);
        TowerDesignPanel towerDesignDialog = new TowerDesignPanel(this);
        mainPanel.add(towerDesignDialog);
        myCreatedTowers = new JScrollPane(myScrollPanel);
        myCreatedTowers.setPreferredSize(new Dimension(380, 500));
        myCreatedTowers.setOpaque(false);
        myCreatedTowers.getViewport().setOpaque(false);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myCreatedTowers.setBorder(BorderFactory
                .createTitledBorder(b, "Created Towers",
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    new Font("Calibri", Font.PLAIN, 20)));
        mainPanel.add(myCreatedTowers);
        return mainPanel;
    }

    public void addTower (File imgSource, String towerName) {
        System.out.println(towerName);
        JLabel towerIcon = new JLabel();
        try {
            Image towerImage = ImageIO.read(imgSource);
            towerIcon.setIcon(new ImageIcon(towerImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JLabel towerNameLabel = new JLabel(towerName);
        towerNameLabel.setFont(Constants.DEFAULT_BODY_FONT);
        myScrollPanel.add(towerNameLabel);
        myScrollPanel.add(towerIcon);

    }

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub

    }
}
