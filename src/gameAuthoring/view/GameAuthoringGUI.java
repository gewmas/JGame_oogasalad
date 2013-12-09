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
import java.awt.Font;
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
    private GameData myGameData;

    public GameAuthoringGUI () {
        myGameData = new GameData();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1190, 780));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainPanel = new ImagePanel("texture0.png");
        myMainPanel.setLayout(new MigLayout("wrap 2"));
        JTabbedPane gameDesignTab = new JTabbedPane();
        gameDesignTab.setPreferredSize(new Dimension(750, 600));

        BasicInfoTab basicInfoTab = new BasicInfoTab();
        BasicInfoDesignController basicInfoDesignController =
                new BasicInfoDesignController(myGameData);
        basicInfoTab.addObserver(basicInfoDesignController);

        MapDesignTab mapDesignTab = new MapDesignTab();
        MapDesignController mapDesignController = new MapDesignController(myGameData);
        mapDesignTab.addObserver(mapDesignController);

        TowerDesignTab towerDesignTab = new TowerDesignTab();
        TowerDesignController towerDesignController = new TowerDesignController(myGameData);
        towerDesignTab.addObserver(towerDesignController);

        EnemyDesignController enemyDesignController = new EnemyDesignController(myGameData);
        EnemyDesignTab enemyDesignTab = new EnemyDesignTab();
        enemyDesignTab.addObserver(enemyDesignController);

        WaveDesignTab waveDesignTab = new WaveDesignTab();
        SkillsDesignTab skillsDesignTab = new SkillsDesignTab();
        SkillsDesignController skillsDesignController = new SkillsDesignController(myGameData);
        skillsDesignTab.addObserver(skillsDesignController);

        EnemyWaveCommunicationController enemyWaveCommController =
                new EnemyWaveCommunicationController();
        enemyWaveCommController.addObserver(waveDesignTab);
        enemyDesignTab.addObserver(enemyWaveCommController);

        TempBarrierDesignController tempBarrierDesignController =
                new TempBarrierDesignController(myGameData);
        TempBarrierDesignTab tempBarrierTab = new TempBarrierDesignTab();
        tempBarrierTab.addObserver(tempBarrierDesignController);

        gameDesignTab.addTab("Basic Info", basicInfoTab.getTab());
        gameDesignTab.setFont(new Font("Calibri", Font.PLAIN, 14));
        gameDesignTab.addTab("Map Design", mapDesignTab.getTab());
        gameDesignTab.addTab("Tower Design", towerDesignTab.getTab());
        gameDesignTab.addTab("Enemy Design", enemyDesignTab.getTab());
        gameDesignTab.addTab("Wave Design", waveDesignTab.getTab());
        gameDesignTab.addTab("Temp Barrier Design", tempBarrierTab.getTab());
        gameDesignTab.addTab("Skills Design", skillsDesignTab.getTab());
        gameDesignTab.addChangeListener(observeTabChange());
        MenuBar menu = new MenuBar(this, myGameData, basicInfoTab, mapDesignTab, waveDesignTab);
        myMainPanel.add(gameDesignTab, "gap 50 20 100 40");
        createUserLibraryTab();
        // myMainPanel.add(myDuvallClippy);
        frame.setJMenuBar(menu);
        frame.setContentPane(myMainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
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
