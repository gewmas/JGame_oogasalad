package gameAuthoring.view;

import gameEngine.controller.Controller;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca Lai & Susan Zhang
 *         MainPanel combines the GameAuthoringGUI with the game engine. It allows the user to pick
 *         between the two options.
 * 
 */
public class MainPanel {
    private JFrame myFrame;
    private static final String AUTHORING_BUTTON_FORMATTING = "gap 300 50 300 0";
    private static final String ENGINE_BUTTON_FORMATTING = "gap 300 50 30 0";
    private static final Dimension DEFAULT_BUTTON_DIMENSION = new Dimension(300, 100);
    private static final String DEFAULT_WRAP_MODE = "wrap 1";
    private static final Dimension FRAME_DIMENSION = new Dimension(950, 750);
    private static final String DEFAULT_PANEL_IMAGE = "tower_main2.png";
    private static final String DEFAULT_AUTHORING_BUTTON_IMAGE = "create_game.png";
    private static final String DEFAULT_ENGINE_BUTTON_IMAGE = "play_game.png";

    /**
     * Creates new MainPanel
     */
    public MainPanel () {
        myFrame = new JFrame();
        ImagePanel mainPanel = new ImagePanel(DEFAULT_PANEL_IMAGE);
        mainPanel.setLayout(new MigLayout(DEFAULT_WRAP_MODE));
        ImageButton authoringMode = new ImageButton(DEFAULT_AUTHORING_BUTTON_IMAGE);
        authoringMode.setPreferredSize(DEFAULT_BUTTON_DIMENSION);
        authoringMode.addMouseListener(setAuthoringListener());
        ImageButton gameMode = new ImageButton(DEFAULT_ENGINE_BUTTON_IMAGE);
        gameMode.addMouseListener(setEngineListener());
        gameMode.setPreferredSize(DEFAULT_BUTTON_DIMENSION);
        mainPanel.add(authoringMode, AUTHORING_BUTTON_FORMATTING);
        mainPanel.add(gameMode, ENGINE_BUTTON_FORMATTING);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setPreferredSize(FRAME_DIMENSION);
        myFrame.setContentPane(mainPanel);
        myFrame.pack();
        myFrame.setLocationByPlatform(true);
        myFrame.setResizable(false);
    }

    /**
     * Allows MainPanel to be displayed
     */
    public void show () {
        myFrame.setVisible(true);
    }

    /**
     * Disposes of MainPanel
     */
    private void close () {
        myFrame.dispose();
    }

    /**
     * Bring up game engine
     * 
     * @return MouseAdapter that allows game engine to be brought up on click
     */
    private MouseAdapter setEngineListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                Controller controller = new Controller();
                controller.promptForFile();
                close();
            }
        };
        return listener;
    }

    /**
     * Bring up game authoring GUI
     * 
     * @return MouseAdapater that allows game authoring environment to be brought up on click
     */
    private MouseAdapter setAuthoringListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                GameAuthoringGUI gui = new GameAuthoringGUI();
            }
        };
        return listener;
    }

    public static void main (String[] args) {
        MainPanel panel = new MainPanel();
        panel.show();
    }
}
