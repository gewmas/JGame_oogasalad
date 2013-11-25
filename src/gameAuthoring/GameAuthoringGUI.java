package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.menuBar.MenuBar;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;


public class GameAuthoringGUI implements Observer {

    protected JFrame myFrame;
    protected JPanel myMainPanel;
    static File mySelectedImage = null;
    private BasicInfoTab myBasicInfoTab;
    private MapDesignTab myMapDesignTab;
    private TowerDesignTab myTowerDesignTab;
    private EnemyDesignTab myEnemyDesignTab;
    private WaveDesignTab myWaveDesignTab;
    private MiscellaneousTab myMiscellaneousTab;
    private UserLibraryMainTab myUserLibraryPanel;
    private Image currentImage;

    // TO DO: Get rid of magic numbers
    public GameAuthoringGUI () {
        GameData gameData = new GameData();
        myFrame = new JFrame();
        JLabel title = new JLabel();
        Image titleImage;
        try {
            titleImage = ImageIO.read(this.getClass().getResource("title.png"));
            title.setIcon(new ImageIcon(titleImage));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        myFrame.setPreferredSize(new Dimension(1200, 1000));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainPanel = new ImagePanel(new MigLayout("wrap 2"));
        JTabbedPane gameDesignTab = new JTabbedPane();
        gameDesignTab.setPreferredSize(new Dimension(750, 650));
        myBasicInfoTab = new BasicInfoTab(gameData);
        myMapDesignTab = new MapDesignTab(gameData);
        myTowerDesignTab = new TowerDesignTab(gameData);
        myEnemyDesignTab = new EnemyDesignTab(gameData);
        myWaveDesignTab = new WaveDesignTab(gameData);
        myMiscellaneousTab = new MiscellaneousTab(gameData);
        myUserLibraryPanel = new UserLibraryMainTab(this);

        gameDesignTab.addTab("Basic Info", myBasicInfoTab.getTab());
        gameDesignTab.setFont(new Font("Calibri", Font.PLAIN, 14));
        gameDesignTab.addTab("Map Design", myMapDesignTab.getTab());
        gameDesignTab.addTab("Tower Design", myTowerDesignTab.getTab());
        gameDesignTab.addTab("Enemy Design", myEnemyDesignTab.getTab()); 
        gameDesignTab.addTab("Wave Design", myWaveDesignTab.getTab());
        gameDesignTab.addTab("Miscellaneous", myMiscellaneousTab.getTab());
        MenuBar menu = new MenuBar(gameData, myBasicInfoTab, myMapDesignTab, myWaveDesignTab);
        myMainPanel.add(title, "span 2, align left, gap 0 0 30 0");
        myMainPanel.add(gameDesignTab, "gap 50 20 30 40");
        myMainPanel.add(myUserLibraryPanel);
        myFrame.setJMenuBar(menu);
        myFrame.setContentPane(myMainPanel);
        myFrame.pack();
        myFrame.setLocationByPlatform(true);
        myFrame.setVisible(true);
        myFrame.setResizable(false);
    }

    public static void main (String[] arg) {
        GameAuthoringGUI gameAuthoringGUI = new GameAuthoringGUI();
    }

    @Override
    public void update (Observable arg0, Object arg1) {
        if (arg1 instanceof File) {
            File imageFile = (File) arg1;
            mySelectedImage = imageFile;
            Image image;
            try {
                image = ImageIO.read(imageFile);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Cursor c = toolkit.createCustomCursor(image, new Point(myMainPanel.getX(),
                                                                       myMainPanel.getY()), "img");
                myMainPanel.setCursor(c);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (arg1 instanceof Integer) {
            myMainPanel.setCursor(Cursor.getDefaultCursor());
        }
    }
}
