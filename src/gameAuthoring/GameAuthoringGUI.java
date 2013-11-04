package gameAuthoring;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class GameAuthoringGUI {

    public GameAuthoringGUI () {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        JTabbedPane basicInfoTab = new BasicInfoTab().getTab();
        JTabbedPane mapDesignTab = new MapDesignTab().getTab();
        JTabbedPane towerDesignTab = new TowerDesignTab().getTab();
        JTabbedPane enemyDesignTab = new EnemyDesignTab().getTab();
        JTabbedPane levelDesignTab = new LevelDesignTab().getTab();
        mainPanel.add(basicInfoTab);
        mainPanel.add(mapDesignTab);
        mainPanel.add(towerDesignTab);
        mainPanel.add(enemyDesignTab);
        mainPanel.add(levelDesignTab);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main (String[] arg) {
        GameAuthoringGUI gameAuthoringGUI = new GameAuthoringGUI();
    }
}
