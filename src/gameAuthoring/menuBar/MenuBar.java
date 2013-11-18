package gameAuthoring.menuBar;

import gameAuthoring.BasicInfoTab;
import gameAuthoring.GameData;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;


public class MenuBar extends JMenuBar {
    private GameData myGameData;
    private BasicInfoTab myBasicInfoTab;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/JSON");
    private Parser myParser;

    public MenuBar (GameData gameData, BasicInfoTab basicInfoTab) {
        add(fileMenu());
        myGameData = gameData;
        myBasicInfoTab = basicInfoTab;
    }

    private JMenu fileMenu () {
        JMenu menu = new JMenu("File");
        menu.add(new AbstractAction("Save") {

            @Override
            public void actionPerformed (ActionEvent e) {
                myGameData.writeToFile();
            }

        });

        menu.add(new AbstractAction("Load") {
            @Override
            public void actionPerformed (ActionEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {

                    try {
                        Scanner s = new Scanner(INPUT_CHOOSER.getSelectedFile());
                        myParser = new Parser(s);
                        myBasicInfoTab.load(myParser);
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
