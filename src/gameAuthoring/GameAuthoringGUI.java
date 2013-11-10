package gameAuthoring;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class GameAuthoringGUI {

    // TO DO: Get rid of magic numbers
    public GameAuthoringGUI () {
        GameData gameData = new GameData();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane mainPane = new JTabbedPane();
        mainPane.setPreferredSize(new Dimension(650, 650));
        JPanel basicInfoTab = new BasicInfoTab(gameData).getTab();
        JPanel mapDesignTab = new MapDesignTab(gameData).getTab();
        JPanel towerDesignTab = new TowerDesignTab(gameData).getTab();
        JPanel enemyDesignTab = new EnemyDesignTab(gameData).getTab();
        JPanel levelDesignTab = new LevelDesignTab(gameData).getTab();
        mainPane.addTab("Basic Info", basicInfoTab);
        mainPane.addTab("Map Design", mapDesignTab);
        mainPane.addTab("Tower Design", towerDesignTab);
        mainPane.addTab("Enemy Design", enemyDesignTab);
        mainPane.addTab("Level Design", levelDesignTab);
        frame.setContentPane(mainPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main (String[] arg) {
        GameAuthoringGUI gameAuthoringGUI = new GameAuthoringGUI();
    }
}
