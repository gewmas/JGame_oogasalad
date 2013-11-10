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
    private JPanel myPanel;

    public TowerDesignTab () {
        getTab();
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel(new MigLayout());
        myPanel = new JPanel(new MigLayout("wrap 2"));
        JButton button = new JButton("Create New Tower Type");
        button.addMouseListener(createNewTowerListener(this));
        panel.add(button, "span 2, align center");
        myCreatedTowers = new JScrollPane(myPanel);
        myCreatedTowers.setPreferredSize(new Dimension(300, 300));
        panel.add(myCreatedTowers);
        return panel;
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
        myPanel.add(towerNameLabel);
        myPanel.add(towerIcon);

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
