package gameAuthoring;

import gameAuthoring.menuBar.MenuBar;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


public class GameAuthoringGUI {
    private BasicInfoTab myBasicInfoTab;
    private MapDesignTab myMapDesignTab;
    private TowerDesignTab myTowerDesignTab;
    private EnemyDesignTab myEnemyDesignTab;
    private LevelDesignTab myLevelDesignTab;
    private MiscellaneousTab myMiscellaneousTab;

    // TO DO: Get rid of magic numbers
    public GameAuthoringGUI () {
        GameData gameData = new GameData();
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2"));
        JTabbedPane mainPane = new JTabbedPane();
        mainPane.setPreferredSize(new Dimension(750, 650));
        myBasicInfoTab = new BasicInfoTab(gameData);
        myMapDesignTab = new MapDesignTab(gameData);
        myTowerDesignTab = new TowerDesignTab(gameData);
        myEnemyDesignTab = new EnemyDesignTab(gameData);
        myLevelDesignTab = new LevelDesignTab(gameData);
        myMiscellaneousTab = new MiscellaneousTab(gameData);
        JPanel imageLibrary = new JPanel();
        Border b = BorderFactory.createLoweredBevelBorder();
        imageLibrary.setBorder(b);
        imageLibrary.setPreferredSize(new Dimension(300, 500));
        mainPane.addTab("Basic Info", myBasicInfoTab.getTab());
        mainPane.addTab("Map Design", myMapDesignTab.getTab());
        mainPane.addTab("Tower Design", myTowerDesignTab.getTab());
        mainPane.addTab("Enemy Design", myEnemyDesignTab.getTab());
        mainPane.addTab("Level Design", myLevelDesignTab.getTab());
        mainPane.addTab("Miscellaneous", myMiscellaneousTab.getTab());
        MenuBar menu = new MenuBar(gameData, myBasicInfoTab, myMapDesignTab);
        mainPanel.add(mainPane);
        mainPanel.add(imageLibrary);
        frame.setJMenuBar(menu);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main (String[] arg) {
        GameAuthoringGUI gameAuthoringGUI = new GameAuthoringGUI();
    }
}
