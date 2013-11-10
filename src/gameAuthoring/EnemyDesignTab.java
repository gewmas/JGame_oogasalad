package gameAuthoring;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class EnemyDesignTab extends Tab {

    private JScrollPane myCreatedEnemies;
    private JPanel myScrollPanel;

    public EnemyDesignTab (GameData gameData) {
        super(gameData);
    }

    @Override
    public JPanel getTab () {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2"));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        myScrollPanel = new JPanel(new MigLayout("wrap 2, align center"));
        EnemyDesignPanel enemyDesignPanel = new EnemyDesignPanel(this);
        mainPanel.add(enemyDesignPanel);
        myCreatedEnemies = new JScrollPane(myScrollPanel);
        myCreatedEnemies.setPreferredSize(new Dimension(200, 400));
        mainPanel.add(myCreatedEnemies, "pad 30 60 10 40");
        return mainPanel;
    }

    public void addEnemy (File imgSource, String enemyName) {
        JLabel enemyIcon = new JLabel();
        try {
            Image enemyImage = ImageIO.read(imgSource);
            enemyIcon.setIcon(new ImageIcon(enemyImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JLabel enemyNameLabel = new JLabel(enemyName);
        myScrollPanel.add(enemyNameLabel);
        myScrollPanel.add(enemyIcon);

    }

}
