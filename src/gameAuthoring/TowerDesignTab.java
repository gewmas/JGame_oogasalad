package gameAuthoring;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class TowerDesignTab extends Tab {
    
    private JScrollPane myCreatedTowers;
    private JPanel myPanel;
    
    public TowerDesignTab () {
        getTab();
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        myPanel = new JPanel();
        JButton button = new JButton("Create New Tower Type");      
        button.addMouseListener(createNewTowerListener(this));
        myCreatedTowers = new JScrollPane();
        myPanel.add(button);
        myPanel.add(myCreatedTowers);
        return myPanel;
    }  
    
    public void addTower(File imgSource, String towerName){
        JLabel towerIcon = new JLabel();
        try {
            Image towerImage = ImageIO.read(imgSource);
            towerIcon.setIcon(new ImageIcon(towerImage));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        JLabel towerNameLabel = new JLabel("Tower Name");
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
