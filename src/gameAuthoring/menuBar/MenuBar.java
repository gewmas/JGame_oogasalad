package gameAuthoring.menuBar;

import gameAuthoring.model.GameData;
import gameAuthoring.view.BasicInfoTab;
import gameAuthoring.view.DuvallClippy;
import gameAuthoring.view.GameAuthoringGUI;
import gameAuthoring.view.MapDesignTab;
import gameAuthoring.view.StyleConstants;
import gameAuthoring.view.WaveDesignTab;
import gameEngine.parser.Parser;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;


/**
 * @author Rebecca Lai & Susan Zhang
 *         MenuBar is the menu bar used in GameAuthoringGUI. It allows the user to save a game or
 *         load settings from an existing game.
 * 
 */
public class MenuBar extends JMenuBar {
    private GameData myGameData;
    private BasicInfoTab myBasicInfoTab;
    private MapDesignTab myMapDesignTab;
    private WaveDesignTab myWaveDesignTab;

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/JSON");
    private Parser myParser;
    private GameAuthoringGUI myGameAuthoringGUI;

    /**
     * Creates new MenuBar
     * 
     * @param gameAuthoringGUI is GameAuthoringGUI that displays MenuBar
     * @param gameData is GameData to which information is written
     * @param basicInfoTab is BasicInfoTab that will be re-created when user selects "Load" option
     * @param mapDesignTab is MapDesignTab that will be re-created when user selects "Load" option
     * @param waveDesignTab is WaveDesignTab that will be re-created when user selects "Load" option
     */
    public MenuBar (GameAuthoringGUI gameAuthoringGUI,
                    GameData gameData,
                    BasicInfoTab basicInfoTab,
                    MapDesignTab mapDesignTab,
                    WaveDesignTab waveDesignTab) {
        add(fileMenu());
        add(showMenu());
        myGameAuthoringGUI = gameAuthoringGUI;
        myGameData = gameData;
        myBasicInfoTab = basicInfoTab;
        myMapDesignTab = mapDesignTab;
        myWaveDesignTab = waveDesignTab;
    }

    /**
     * Display option of menu bar
     * 
     * @return JMenu to be displayed in GameAuthoringGUI
     */
    private JMenu showMenu () {
        JMenu menu = new JMenu(StyleConstants.resourceBundle.getString("Show"));
        menu.add(new AbstractAction(StyleConstants.resourceBundle.getString("Clippy")) {
            public void actionPerformed (ActionEvent e) {
                DuvallClippy duvall = new DuvallClippy();
                myGameAuthoringGUI.addObserver(duvall);
            }
        });
        return menu;
    }

    /**
     * Show option of menu bar
     * 
     * @return JMenu to be displayed in GameAuthoringGUI
     */
    private JMenu fileMenu () {
        JMenu menu = new JMenu(StyleConstants.resourceBundle.getString("File"));
        menu.add(new AbstractAction(StyleConstants.resourceBundle.getString("Save")) {

            @Override
            public void actionPerformed (ActionEvent e) {
                myGameData.writeToFile();
            }
        });
        menu.add(new AbstractAction(StyleConstants.resourceBundle.getString("Load")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {

                    try {
                        Scanner s = new Scanner(INPUT_CHOOSER.getSelectedFile());
                        myParser = new Parser(s);
                        myGameAuthoringGUI.loadJSON(myParser);
                        //myBasicInfoTab.loadJSON(myParser);
                        myMapDesignTab.loadJSON(myParser);
                        //myWaveDesignTab.loadJSON(myParser);
                    }
                    catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(null,
                                                      "Error loading");
                        e1.printStackTrace();
                    }
                }
            }
        });

        return menu;
    }

}
