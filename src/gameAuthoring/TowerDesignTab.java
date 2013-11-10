package gameAuthoring;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
        mainPanel.setPreferredSize(new Dimension(500, 500));
        myScrollPanel = new JPanel(new MigLayout("wrap 2, align center"));
        JButton button = new JButton("Create New Tower Type");
        button.addMouseListener(createNewTowerListener(this));
        mainPanel.add(button, "pad 0 20 0 20");
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

    public MouseAdapter createNewTowerListener (final TowerDesignTab towerDesignTab) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                TowerDesignDialog towerDesignDialog = new TowerDesignDialog(towerDesignTab);
                towerDesignDialog.setSize(new Dimension(300, 350));
                towerDesignDialog.setVisible(true);
            }
        };
        return listener;
    }
}
