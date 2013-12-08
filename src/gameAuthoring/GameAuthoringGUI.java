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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;


public class GameAuthoringGUI {

    protected JFrame myFrame;
    protected static JPanel myMainPanel;
    protected static File mySelectedImage = null;
    private BasicInfoTab myBasicInfoTab;
    private MapDesignTab myMapDesignTab;
    private TowerDesignTab myTowerDesignTab;
    private EnemyDesignTab myEnemyDesignTab;
    private WaveDesignTab myWaveDesignTab;
    private SkillsDesignTab mySkillsDesignTab;
    private TempBarrierDesignTab myTempBarrierTab;
    private UserLibraryMainTab myUserLibraryTab;
    protected static ImageLabel myImageLabel = null;
    protected static AudioLabel myAudioLabel = null;

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
        myFrame.setPreferredSize(new Dimension(1200, 800));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainPanel = new ImagePanel("texture0.jpg");
        myMainPanel.setLayout(new MigLayout("wrap 2"));
        JTabbedPane gameDesignTab = new JTabbedPane();
        gameDesignTab.setPreferredSize(new Dimension(750, 600));
        myBasicInfoTab = new BasicInfoTab(gameData);
        myMapDesignTab = new MapDesignTab(gameData);
        myTowerDesignTab = new TowerDesignTab(gameData);
        myEnemyDesignTab = new EnemyDesignTab(gameData);
        myWaveDesignTab = new WaveDesignTab(gameData);
        mySkillsDesignTab = new SkillsDesignTab(gameData);
        myTempBarrierTab = new TempBarrierDesignTab(gameData);
        myUserLibraryTab = new UserLibraryMainTab(gameData);

        gameDesignTab.addTab("Basic Info", myBasicInfoTab.getTab());
        gameDesignTab.setFont(new Font("Calibri", Font.PLAIN, 14));
        gameDesignTab.addTab("Map Design", myMapDesignTab.getTab());
        gameDesignTab.addTab("Tower Design", myTowerDesignTab.getTab());
        gameDesignTab.addTab("Enemy Design", myEnemyDesignTab.getTab());
        gameDesignTab.addTab("Wave Design", myWaveDesignTab.getTab());
        gameDesignTab.addTab("Temp Barrier Design", myTempBarrierTab.getTab());
        gameDesignTab.addTab("Skills Design", mySkillsDesignTab.getTab());
        MenuBar menu = new MenuBar(gameData, myBasicInfoTab, myMapDesignTab, myWaveDesignTab);
        myMainPanel.add(title, "span 2, align left, gap 0 0 15 0");
        myMainPanel.add(gameDesignTab, "gap 50 20 20 40");
        myMainPanel.add(myUserLibraryTab);
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

    public static final void setCursor (File imageFile) {
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

    public final static void setCursorNull () {
        myMainPanel.setCursor(Cursor.getDefaultCursor());
    }
}
