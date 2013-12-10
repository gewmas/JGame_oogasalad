package gameAuthoring.view;

import gameAuthoring.controllers.BasicInfoDesignController;
import gameAuthoring.controllers.EnemyDesignController;
import gameAuthoring.controllers.EnemyWaveCommunicationController;
import gameAuthoring.controllers.MapDesignController;
import gameAuthoring.controllers.SkillsDesignController;
import gameAuthoring.controllers.TempBarrierDesignController;
import gameAuthoring.controllers.TowerDesignController;
import gameAuthoring.controllers.UserImagesController;
import gameAuthoring.controllers.WaveDesignController;
import gameAuthoring.model.GameData;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca and Susan Zhang
 *         GameAuthoringGUI is the main game authoring environment. Tabs separate the game authoring
 *         process into basic info design, map design, tower design, enemy design, temporary barrier
 *         design, and skills design.
 * 
 */
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
    private UserImagesTab myUserImagesTab;
    private UserSoundsTab myUserSoundsTab;
    private static final String GAME_DESIGN_TAB_FORMATTING = "gap 50 20 100 40";
    private static final String DEFAULT_PANEL_BG_IMAGE_NAME = "texture0.png";
    private static final Dimension GAME_DESIGN_TAB_DEFAULT_DIMENSION = new Dimension(750, 600);
    private static final Dimension RESOURCE_LIBRARY_DIMENSION = new Dimension(300, 600);
    private static final Dimension FRAME_DEFAULT_DIMENSION = new Dimension(1190, 780);
    public static final String FILE_PREFIX = System.getProperties().getProperty("user.dir") + "/src/resources/img/";

    /**
     * Constructor for GameAuthoringGUI that sets up all design tabs in the main frame
     * Creates new GameAuthoringGUI
     */
    public GameAuthoringGUI () {
        myGameData = new GameData();
        JFrame frame = new JFrame();
        frame.setPreferredSize(FRAME_DEFAULT_DIMENSION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainPanel = new ImagePanel(DEFAULT_PANEL_BG_IMAGE_NAME);
        myMainPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myGameDesignTab = new JTabbedPane();
        myGameDesignTab.setPreferredSize(GAME_DESIGN_TAB_DEFAULT_DIMENSION);
        myGameDesignTab.setFont(StyleConstants.DEFAULT_BODY_FONT);
        addBasicInfoTab();
        addMapDesignTab();
        addTowerDesignTab();
        addEnemyWaveDesignTab();
        addTemporaryBarrierDesignTab();
        addSkillsDesignTab();
        myGameDesignTab.addChangeListener(observeTabChange());
        MenuBar menu =
                new MenuBar(this, myGameData, myBasicInfoTab,  myMapDesignTab, myEnemyDesignTab, myTowerDesignTab, myWaveDesignTab);
        myMainPanel.add(myGameDesignTab, GAME_DESIGN_TAB_FORMATTING);
        createUserLibraryTab();
        frame.setJMenuBar(menu);
        frame.setContentPane(myMainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Adds tab and label for basic information design into the main panel
     */
    private void addBasicInfoTab () {
        myBasicInfoTab = new BasicInfoTab();
        BasicInfoDesignController basicInfoDesignController =
                new BasicInfoDesignController(myGameData);
        myBasicInfoTab.addObserver(basicInfoDesignController);
        myGameDesignTab.addTab(StyleConstants.resourceBundle.getString("BasicInfoTab"),
                               myBasicInfoTab.getTab());
    }

    /**
     * Adds tab and label for map design into the main panel
     */
    private void addMapDesignTab () {
        myMapDesignTab = new MapDesignTab();
        MapDesignController mapDesignController = new MapDesignController(myGameData);
        myMapDesignTab.addObserver(mapDesignController);
        myGameDesignTab.addTab(StyleConstants.resourceBundle.getString("MapTab"),
                               myMapDesignTab.getTab());
    }

    /**
     * 
     * Adds tab and label for tower design into the main panel
     */
    private void addTowerDesignTab () {
        myTowerDesignTab = new TowerDesignTab();
        TowerDesignController towerDesignController = new TowerDesignController(myGameData);
        myTowerDesignTab.addObserver(towerDesignController);
        myGameDesignTab.addTab(StyleConstants.resourceBundle.getString("TowerTab"),
                               myTowerDesignTab.getTab());
    }

    /**
     * Adds controller to communicate between EnemyDesignTab and WaveDesignTab
     */
    private void addEnemyWaveDesignTab () {
        EnemyDesignController enemyDesignController = new EnemyDesignController(myGameData);
        myEnemyDesignTab = new EnemyDesignTab();
        myEnemyDesignTab.addObserver(enemyDesignController);
        EnemyWaveCommunicationController enemyWaveCommController =
                new EnemyWaveCommunicationController();
        myWaveDesignTab = new WaveDesignTab();
        WaveDesignController waveDesignController = new WaveDesignController(myGameData);
        myWaveDesignTab.addObserver(waveDesignController);
        enemyWaveCommController.addObserver(myWaveDesignTab);
        myEnemyDesignTab.addObserver(enemyWaveCommController);
        myGameDesignTab.addTab(StyleConstants.resourceBundle.getString("EnemyTab"),
                               myEnemyDesignTab.getTab());
        myGameDesignTab.addTab(StyleConstants.resourceBundle.getString("WaveTab"),
                               myWaveDesignTab.getTab());
    }

    /**
     * Adds tab and label for temporary barrier design into main panel
     */
    private void addTemporaryBarrierDesignTab () {
        TempBarrierDesignController tempBarrierDesignController =
                new TempBarrierDesignController(myGameData);
        myTempBarrierTab = new TempBarrierDesignTab();
        myTempBarrierTab.addObserver(tempBarrierDesignController);
        myGameDesignTab.addTab(StyleConstants.resourceBundle.getString("TempBarrierTab"),
                               myTempBarrierTab.getTab());
    }

    /**
     * Adds tab and label for skills design tab into main panel
     */
    private void addSkillsDesignTab () {
        mySkillsDesignTab = new SkillsDesignTab();
        SkillsDesignController skillsDesignController = new SkillsDesignController(myGameData);
        mySkillsDesignTab.addObserver(skillsDesignController);
        myGameDesignTab.addTab(StyleConstants.resourceBundle.getString("SkillsTab"),
                               mySkillsDesignTab.getTab());
    }

    /**
     * Sets up user library in main panel
     * User library contains resources (images and audio) that user loads
     */
    private void createUserLibraryTab () {
        JTabbedPane userLibrary = new JTabbedPane();
        userLibrary.setPreferredSize(RESOURCE_LIBRARY_DIMENSION);
        myUserImagesTab = new UserImagesTab();
        UserImagesController userImagesController = new UserImagesController(myGameData);
        myUserImagesTab.addObserver(userImagesController);
        myUserSoundsTab = new UserSoundsTab();
        userLibrary.add(StyleConstants.resourceBundle.getString("ImagesTab"),
                        myUserImagesTab.getTab());
        userLibrary.add(StyleConstants.resourceBundle.getString("SoundsTab"),
                        myUserSoundsTab.getTab());
        userLibrary.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myMainPanel.add(userLibrary);
    }

    /**
     * Allows for clicking and dragging of images from user library
     * 
     * @param imageFile is image that cursor should be set to
     */
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

    /**
     * Notify office assistant of tab switches
     * 
     * @return ChangeListener that notifies office assistant of tab navigation
     */
    private ChangeListener observeTabChange () {
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged (ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                String key = sourceTabbedPane.getTitleAt(index);
                setChanged();
                notifyObservers(key);
                clearChanged();
            }
        };
        return changeListener;
    }

    /**
     * Set cursor for entire frame back to default cursor
     */
    public static final void setCursorDefault () {
        myMainPanel.setCursor(Cursor.getDefaultCursor());
    }

    /**
     * @param audio is audio file to be transferred from user library
     */
    public static final void setAudioFile (File audio) {
        mySelectedAudio = audio;
    }

    public static void main (String[] arg) {
        GameAuthoringGUI gameAuthoringGUI = new GameAuthoringGUI();
    }

    /**
     * Method to load image library from JSON
     * 
     * @param p
     */
    public void loadJSON (Parser p) {
        JSONObject resources = p.getJSONObject("resources");
        JSONArray images = (JSONArray) resources.get("image");
        
        for (int i = 0; i < images.length(); i++) {
            JSONObject image = images.getJSONObject(i);
            String id = image.getString("id");
            String url = image.getString("url");

            if (!id.equals("bullet")){
                myUserImagesTab.addImageLabel(new File(FILE_PREFIX + url), id);
            }
            
        }
        
        JSONArray audio = (JSONArray) resources.get("audio");
        
        for (int i = 0; i < audio.length(); i++) {
            JSONObject sound = audio.getJSONObject(i);
            String id = sound.getString("id");
            String url = sound.getString("url");
            myUserSoundsTab.addAudioLabel(new File(FILE_PREFIX + url));
        }
        
        JSONArray animations = (JSONArray) resources.get("animation");

        for (int i = 0; i < animations.length(); i++) {
           JSONObject animation = animations.getJSONObject(i);
           myGameData.addAnimation(animation);
        }
    }

}
