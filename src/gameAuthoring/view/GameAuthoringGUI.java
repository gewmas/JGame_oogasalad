package gameAuthoring.view;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.controllers.BasicInfoDesignController;
import gameAuthoring.controllers.EnemyDesignController;
import gameAuthoring.controllers.EnemyWaveCommunicationController;
import gameAuthoring.controllers.MapDesignController;
import gameAuthoring.controllers.SkillsDesignController;
import gameAuthoring.controllers.TempBarrierDesignController;
import gameAuthoring.controllers.TowerDesignController;
import gameAuthoring.controllers.UserImagesController;
import gameAuthoring.menuBar.MenuBar;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.miginfocom.swing.MigLayout;


public class GameAuthoringGUI extends Observable {

    protected static JPanel myMainPanel;
    protected static File mySelectedImage = null;
    protected static File mySelectedAudio = null;
    protected static ImageLabel myImageLabel = null;
    protected static AudioLabel myAudioLabel = null;
    private BasicInfoTab myBasicInfoTab;
    private MapDesignTab myMapDesignTab;
    private TowerDesignTab myTowerDesignTab;
    private EnemyDesignTab myEnemyDesignTab;
    private WaveDesignTab myWaveDesignTab;
    private TempBarrierDesignTab myTempBarrierTab;
    private SkillsDesignTab mySkillsDesignTab;
    private GameData myGameData;
    private JTabbedPane myGameDesignTab;

    public GameAuthoringGUI () {
        myGameData = new GameData();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1190, 780));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainPanel = new ImagePanel("texture0.png");
        myMainPanel.setLayout(new MigLayout("wrap 2"));
        myGameDesignTab = new JTabbedPane();
        myGameDesignTab.setPreferredSize(new Dimension(750, 600));
        myGameDesignTab.setFont(StyleConstants.DEFAULT_BODY_FONT);

        addBasicInfoTab();
        addMapDesignTab();
        addTowerDesignTab();
        addEnemyWaveDesignTab();
        addTemporaryBarrierDesignTab();
        addSkillsDesignTab();

        myGameDesignTab.addChangeListener(observeTabChange());
        MenuBar menu =
                new MenuBar(this, myGameData, myBasicInfoTab, myMapDesignTab, myWaveDesignTab);
        myMainPanel.add(myGameDesignTab, "gap 50 20 100 40");
        createUserLibraryTab();
        // myMainPanel.add(myDuvallClippy);
        frame.setJMenuBar(menu);
        frame.setContentPane(myMainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void addBasicInfoTab () {
        myBasicInfoTab = new BasicInfoTab();
        BasicInfoDesignController basicInfoDesignController =
                new BasicInfoDesignController(myGameData);
        myBasicInfoTab.addObserver(basicInfoDesignController);
        myGameDesignTab.addTab("Basic Info", myBasicInfoTab.getTab());
    }

    public void addMapDesignTab () {
        myMapDesignTab = new MapDesignTab();
        MapDesignController mapDesignController = new MapDesignController(myGameData);
        myMapDesignTab.addObserver(mapDesignController);
        myGameDesignTab.addTab("Map Design", myMapDesignTab.getTab());
    }

    public void addTowerDesignTab () {
        myTowerDesignTab = new TowerDesignTab();
        TowerDesignController towerDesignController = new TowerDesignController(myGameData);
        myTowerDesignTab.addObserver(towerDesignController);
        myGameDesignTab.addTab("Tower Design", myTowerDesignTab.getTab());
    }

    public void addEnemyWaveDesignTab () {
        EnemyDesignController enemyDesignController = new EnemyDesignController(myGameData);
        myEnemyDesignTab = new EnemyDesignTab();
        myEnemyDesignTab.addObserver(enemyDesignController);
        EnemyWaveCommunicationController enemyWaveCommController =
                new EnemyWaveCommunicationController();
        myWaveDesignTab = new WaveDesignTab();
        enemyWaveCommController.addObserver(myWaveDesignTab);
        myEnemyDesignTab.addObserver(enemyWaveCommController);
        myGameDesignTab.addTab("Enemy Design", myEnemyDesignTab.getTab());
        myGameDesignTab.addTab("Wave Design", myWaveDesignTab.getTab());
    }

    public void addTemporaryBarrierDesignTab () {
        TempBarrierDesignController tempBarrierDesignController =
                new TempBarrierDesignController(myGameData);
        myTempBarrierTab = new TempBarrierDesignTab();
        myTempBarrierTab.addObserver(tempBarrierDesignController);
        myGameDesignTab.addTab("Temp Barrier Design", myTempBarrierTab.getTab());
    }

    public void addSkillsDesignTab () {
        mySkillsDesignTab = new SkillsDesignTab();
        SkillsDesignController skillsDesignController = new SkillsDesignController(myGameData);
        mySkillsDesignTab.addObserver(skillsDesignController);
        myGameDesignTab.addTab("Skills Design", mySkillsDesignTab.getTab());
    }

    private void createUserLibraryTab () {
        JTabbedPane userLibrary = new JTabbedPane();
        userLibrary.setPreferredSize(new Dimension(300, 600));
        UserImagesTab userImagesTab = new UserImagesTab();
        UserImagesController userImagesController = new UserImagesController(myGameData);
        userImagesTab.addObserver(userImagesController);
        UserSoundsTab userSoundsTab = new UserSoundsTab();
        userLibrary.add("Image Library", userImagesTab.getTab());
        userLibrary.add("Sound Library", userSoundsTab.getTab());
        userLibrary.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myMainPanel.add(userLibrary);
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

    private ChangeListener observeTabChange () {
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged (ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                String key = sourceTabbedPane.getTitleAt(index);
                System.out.println(sourceTabbedPane.getTitleAt(index));
                setChanged();
                notifyObservers(key);
                clearChanged();
            }
        };
        return changeListener;
    }

    public static final void setCursorNull () {
        myMainPanel.setCursor(Cursor.getDefaultCursor());
    }

    public static final void setAudioFile (File audio) {
        mySelectedAudio = audio;
    }

    public static void main (String[] arg) {
        GameAuthoringGUI gameAuthoringGUI = new GameAuthoringGUI();
    }

}
