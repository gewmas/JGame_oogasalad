package gameAuthoring;

import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


public class BasicInfoTab extends Tab {
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/img");

    private JTextField myGameName;
    private JTextField myGold;
    private JTextField myLives;
    private JTextField myWindowWidth;
    private JTextField myWindowHeight;
    private JTextField myTilesPerRow;
    private JTextField myDifficultyScale;

    private JLabel mySplashImageLabel;
    private String mySplashImage;

    public BasicInfoTab (GameData gameData) {
        super(gameData);
        getTab();
    }

    // TO DO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2"));
        JPanel subPanel = new JPanel(new MigLayout("wrap 2"));
        JLabel gameName = new JLabel("Game Name");
        JLabel gold = new JLabel("Starting Gold");
        JLabel lives = new JLabel("Starting Lives");
        JLabel title = new JLabel("Basic Game Info");
        JLabel width = new JLabel("Window Width");
        JLabel height = new JLabel("Window Height");
        JLabel tiles = new JLabel("Tiles Per Row");
        JLabel difficultyScale = new JLabel("Difficulty Scale");

        title.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(title, "span 2");

        JButton setSplashImageButton = new JButton("Choose Splash Image");
        setSplashImageButton.addMouseListener(setSplashImageListener());

        mySplashImageLabel = new JLabel();

        JButton setInfoButton = new JButton("Set Info");
        setInfoButton.addMouseListener(setInfoListener());

        myGameName = new JTextField();
        myGameName.setPreferredSize(new Dimension(200, 30));
        myGold = new JTextField();
        myGold.setPreferredSize(new Dimension(200, 30));
        myLives = new JTextField();
        myLives.setPreferredSize(new Dimension(200, 30));
        myWindowWidth = new JTextField();
        myWindowWidth.setPreferredSize(new Dimension(200, 30));
        myWindowHeight = new JTextField();
        myWindowHeight.setPreferredSize(new Dimension(200, 30));
        myTilesPerRow = new JTextField();
        myTilesPerRow.setPreferredSize(new Dimension(200, 30));
        myDifficultyScale = new JTextField();
        myDifficultyScale.setPreferredSize(new Dimension(200, 30));

        subPanel.add(gameName);
        subPanel.add(myGameName);
        subPanel.add(gold);
        subPanel.add(myGold);
        subPanel.add(lives);
        subPanel.add(myLives);
        subPanel.add(width);
        subPanel.add(myWindowWidth);
        subPanel.add(height);
        subPanel.add(myWindowHeight);
        subPanel.add(tiles);
        subPanel.add(myTilesPerRow);
        subPanel.add(difficultyScale);
        subPanel.add(myDifficultyScale);

        subPanel.add(setSplashImageButton);
        subPanel.add(mySplashImageLabel);
        subPanel.add(setInfoButton);
        Border b = BorderFactory.createLoweredBevelBorder();
        subPanel.setBorder(b);
        mainPanel.add(subPanel, "align center");

        return mainPanel;
    }

    public void loadJSON (Parser p) {
        try { 
 
            myGameName.setText(p.getString("name"));          
            myGold.setText(String.valueOf(p.getInt("gold")));
            myLives.setText(String.valueOf(p.getInt("numberOfLives")));
            myWindowWidth.setText(String.valueOf(p.getInt("widthOfWindow")));
            myWindowHeight.setText(String.valueOf(p.getInt("heightOfWindow")));
            myTilesPerRow.setText(String.valueOf(p.getInt("tilesPerRow")));
            myDifficultyScale.setText(String.valueOf(p.getInt("difficultyScale")));
            mySplashImage = p.getString("splashImage");
            mySplashImageLabel.setText(mySplashImage);
        }

        catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null,
                                          "File invalid!");
        }
    }

    public MouseAdapter setInfoListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int gold = Integer.parseInt(myGold.getText());
                int lives = Integer.parseInt(myLives.getText());
                int width = Integer.parseInt(myWindowWidth.getText());
                int height = Integer.parseInt(myWindowHeight.getText());
                int tiles = Integer.parseInt(myTilesPerRow.getText());
                float difficultyScale = Float.parseFloat(myDifficultyScale.getText());
                String name = myGameName.getText();

                // TODO: Set more specific constraints
                if (gold > 0 && lives > 0 && width > 0 && height > 0 && tiles > 0 &&
                    difficultyScale > 1 && mySplashImage != null && name != null) {
                    myGameData.setGold(gold);
                    myGameData.setLives(lives);
                    myGameData.setWindowWidth(width);
                    myGameData.setWindowHeight(height);
                    myGameData.setTilesPerRow(tiles);
                    myGameData.setDifficultyScale(difficultyScale);
                    myGameData.setSplashImage(mySplashImage);
                    myGameData.setGameName(name);
                }

                else {
                    JOptionPane.showMessageDialog(null,
                                                  "One or more fields invalid! Please try again.");
                }

            }
        };
        return listener;

    }

    public MouseAdapter setSplashImageListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    mySplashImage =
                            INPUT_CHOOSER.getSelectedFile().toString()
                                    .replace(System.getProperties().getProperty("user.dir") + "/", "");
                    mySplashImageLabel.setText(mySplashImage);
                }

            }
        };
        return listener;

    }
}
