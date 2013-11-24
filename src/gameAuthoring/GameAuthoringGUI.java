package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.menuBar.MenuBar;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;


public class GameAuthoringGUI {
    private BasicInfoTab myBasicInfoTab;
    private MapDesignTab myMapDesignTab;
    private TowerDesignTab myTowerDesignTab;
    private EnemyDesignTab myEnemyDesignTab;
    private LevelDesignTab myLevelDesignTab;
    private MiscellaneousTab myMiscellaneousTab;
    private SimmulationTab mySimmulationTab;
    private UserLibraryMainTab myUserLibraryPanel;

    // TO DO: Get rid of magic numbers
    public GameAuthoringGUI () {
        GameData gameData = new GameData();
        JFrame frame = new JFrame();
        JLabel title = new JLabel();
        Image titleImage;
        try {
            titleImage = ImageIO.read(this.getClass().getResource("title.png"));
            title.setIcon(new ImageIcon(titleImage));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        frame.setPreferredSize(new Dimension(1200, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2")) {
            @Override
            protected void paintComponent (Graphics grphcs) {
                Image img = null;
                try {
                    img = ImageIO.read(this.getClass().getResource("texture0.jpg"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                int h = img.getHeight(null);
                int w = img.getWidth(null);

                if (w > this.getWidth() || w < this.getWidth()) {
                    img = img.getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT);
                    h = img.getHeight(null);
                }
                if (h > this.getHeight() || h < this.getHeight()) {
                    img = img.getScaledInstance(-1, getHeight(), Image.SCALE_DEFAULT);
                }
                int x = (getWidth() - img.getWidth(null)) / 2;
                int y = (getHeight() - img.getHeight(null)) / 2;
                grphcs.drawImage(img, x, y, null);
            }
        };
        mainPanel.setOpaque(false);
        JTabbedPane gameDesignTab = new JTabbedPane();
        gameDesignTab.setPreferredSize(new Dimension(750, 650));
        myBasicInfoTab = new BasicInfoTab(gameData);
        myMapDesignTab = new MapDesignTab(gameData);
        myTowerDesignTab = new TowerDesignTab(gameData);
        myEnemyDesignTab = new EnemyDesignTab(gameData);
        myLevelDesignTab = new LevelDesignTab(gameData);
        myMiscellaneousTab = new MiscellaneousTab(gameData);
        mySimmulationTab = new SimmulationTab(gameData);
        myUserLibraryPanel = new UserLibraryMainTab();
        gameDesignTab.addTab("Basic Info", myBasicInfoTab.getTab());
        gameDesignTab.setFont(new Font("Calibri", Font.PLAIN, 14));
        gameDesignTab.addTab("Map Design", myMapDesignTab.getTab());
        gameDesignTab.addTab("Tower Design", myTowerDesignTab.getTab());
        gameDesignTab.addTab("Enemy Design", myEnemyDesignTab.getTab());
        gameDesignTab.addTab("Level Design", myLevelDesignTab.getTab());
        gameDesignTab.addTab("Miscellaneous", myMiscellaneousTab.getTab());
        gameDesignTab.addTab("Simmulate", mySimmulationTab.getTab());
        MenuBar menu = new MenuBar(gameData, myBasicInfoTab, myMapDesignTab);
        mainPanel.add(title, "span 2, align left, gap 0 0 30 0");
        mainPanel.add(gameDesignTab, "gap 50 20 30 40");
        mainPanel.add(myUserLibraryPanel);
        frame.setJMenuBar(menu);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main (String[] arg) {
        GameAuthoringGUI gameAuthoringGUI = new GameAuthoringGUI();
    }
}
