package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.menuBar.MenuBar;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class GameAuthoringGUI {
    private BasicInfoTab myBasicInfoTab;
    private MapDesignTab myMapDesignTab;
    private TowerDesignTab myTowerDesignTab;
    private EnemyDesignTab myEnemyDesignTab;
    private LevelDesignTab myLevelDesignTab;
    private MiscellaneousTab myMiscellaneousTab;
    private SimmulationTab mySimmulationTab;
    
    // TO DO: Get rid of magic numbers
    public GameAuthoringGUI () {
        GameData gameData = new GameData();
        JFrame frame = new JFrame();
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane mainPane = new JTabbedPane();
        mainPane.setPreferredSize(new Dimension(650, 650));
        myBasicInfoTab = new BasicInfoTab(gameData);
        myMapDesignTab = new MapDesignTab(gameData);
        myTowerDesignTab = new TowerDesignTab(gameData);
        myEnemyDesignTab = new EnemyDesignTab(gameData);
        myLevelDesignTab = new LevelDesignTab(gameData);
        myMiscellaneousTab = new MiscellaneousTab(gameData);
        mySimmulationTab = new SimmulationTab(gameData);
        mainPane.addTab("Basic Info", myBasicInfoTab.getTab());
        mainPane.addTab("Map Design", myMapDesignTab.getTab());
        mainPane.addTab("Tower Design", myTowerDesignTab.getTab());
        mainPane.addTab("Enemy Design", myEnemyDesignTab.getTab());
        mainPane.addTab("Level Design", myLevelDesignTab.getTab());
        mainPane.addTab("Miscellaneous", myMiscellaneousTab.getTab());
        mainPane.addTab("Simmulate", mySimmulationTab.getTab());
        MenuBar menu = new MenuBar(gameData, myBasicInfoTab, myMapDesignTab);
        frame.setJMenuBar(menu);
        frame.setContentPane(mainPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
    }

    public static void main (String[] arg) {
        GameAuthoringGUI gameAuthoringGUI = new GameAuthoringGUI();
    }
}
