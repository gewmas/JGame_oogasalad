package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.menuBar.MenuBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
        JLabel title = new JLabel("G a m e   D e s i g n   E n v i r o n m e n t");
        title.setFont(new Font("Calibri", Font.ITALIC, 50));
        title.setForeground(new Color(120, 120, 120));
        frame.setPreferredSize(new Dimension(1200, 900));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2")) {
            @Override
            protected void paintComponent (Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.setColor(Color.red);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp =
                        new GradientPaint(0, 0,
                                          getBackground().brighter().brighter(), 0, getHeight(),
                                          getBackground().darker().darker().darker());

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(grphcs);
                // Image img = null;
                // try {
                // img = ImageIO.read(this.getClass().getResource("frame.png"));
                // }
                // catch (IOException e) {
                // e.printStackTrace();
                // }
                // int h = img.getHeight(null);
                // int w = img.getWidth(null);
                //
                // if (w > this.getWidth() || w < this.getWidth()) {
                // img = img.getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT);
                // h = img.getHeight(null);
                // }
                // if (h > this.getHeight() || h < this.getHeight()) {
                // img = img.getScaledInstance(-1, getHeight(), Image.SCALE_DEFAULT);
                // }
                // int x = (getWidth() - img.getWidth(null)) / 2;
                // int y = (getHeight() - img.getHeight(null)) / 2;
                // grphcs.drawImage(img, x, y, null);
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
        mainPanel.add(title, "span 2, align right");
        mainPanel.add(gameDesignTab, "gap 50 20 30 40");
        mainPanel.add(myUserLibraryPanel);
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
