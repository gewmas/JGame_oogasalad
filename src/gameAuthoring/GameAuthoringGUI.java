package gameAuthoring;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class GameAuthoringGUI {

    public GameAuthoringGUI () {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane mainPane = new JTabbedPane();
        mainPane.setPreferredSize(new Dimension(500, 500));
        JPanel basicInfoTab = new BasicInfoTab().getTab();
        JPanel mapDesignTab = new MapDesignTab().getTab();
        JPanel towerDesignTab = new TowerDesignTab().getTab();
        JPanel enemyDesignTab = new EnemyDesignTab().getTab();
        JPanel levelDesignTab = new LevelDesignTab().getTab();
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
