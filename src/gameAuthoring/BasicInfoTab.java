package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.menuBar.Simulator;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


/**
 * Subclass of Tab that contains Swing components to input or load basic game information such as
 * game name, starting quantity of gold, splash image, etc.
 * 
 * 
 * 
 */
public class BasicInfoTab extends Tab {
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/img");

    private JTextField myGameName;
    private JTextField myGold;
    private JTextField myLives;

    private JLabel mySplashImageLabel;
    private String mySplashImage;
    private JButton simulateButton;
    public BasicInfoTab (GameData gameData) {
        super(gameData);
        getTab();
    }

    // TO DO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel mainPanel = new GradientPanel(new MigLayout("wrap 2"));
        JPanel subPanel = new JPanel(new MigLayout("wrap 2"));
        subPanel.setOpaque(false);
        JLabel gameName = new JLabel("Game Name");
        gameName.setFont(Constants.defaultBodyFont);

        JLabel gold = new JLabel("Starting Gold");
        gold.setFont(Constants.defaultBodyFont);

        JLabel lives = new JLabel("Starting Lives");
        lives.setFont(Constants.defaultBodyFont);

        JLabel title = new JLabel("Basic Game Info");
        title.setFont(Constants.defaultBodyFont);

        title.setFont(Constants.defaultTitleFont);
        title.setForeground(new Color(80, 80, 80));
        mainPanel.add(title, "span 2");

        JButton setSplashImageButton = new JButton("Choose Splash Image");
        setSplashImageButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        setSplashImageButton.addMouseListener(setSplashImageListener());

        mySplashImageLabel = new JLabel();

        JButton setInfoButton = new JButton("Set Info");
        simulateButton = new JButton("Simulate");
        simulateButton.setEnabled(false);
        simulateButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        simulateButton.addMouseListener(setSimulateListener());
        setInfoButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        setInfoButton.addMouseListener(setInfoListener());

        myGameName = new JTextField();
        myGameName.setPreferredSize(new Dimension(200, 30));
        myGold = new JTextField();
        myGold.setPreferredSize(new Dimension(200, 30));
        myLives = new JTextField();
        myLives.setPreferredSize(new Dimension(200, 30));


        subPanel.add(gameName);
        subPanel.add(myGameName);
        subPanel.add(gold);
        subPanel.add(myGold);
        subPanel.add(lives);
        subPanel.add(myLives);
        

        subPanel.add(setSplashImageButton);
        subPanel.add(mySplashImageLabel);
        
        subPanel.add(setInfoButton);
        subPanel.add(simulateButton);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        subPanel.setBorder(b);
        mainPanel.add(subPanel, "align center");

        return mainPanel;
    }

    public void loadJSON (Parser p) {
        try {
            myGameName.setText(p.getString("name"));
            myGold.setText(String.valueOf(p.getInt("gold")));
            myLives.setText(String.valueOf(p.getInt("numberOfLives")));
            mySplashImage = p.getString("splashImage");
            mySplashImageLabel.setText(mySplashImage);

            setData();
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
                setData();
                
            }
        };
        return listener;

    }
    public MouseAdapter setSimulateListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                Simulator simulator = new Simulator();
                simulator.simulate(myGameData);
                
            }
        };
        return listener;

    }

    private void setData () {
        int gold = Integer.parseInt(myGold.getText());
        int lives = Integer.parseInt(myLives.getText());
        String name = myGameName.getText();

        if (gold > 0 && lives > 0 && mySplashImage != null && name != null) {
            myGameData.setGold(gold);
            myGameData.setLives(lives);
            myGameData.setSplashImage(mySplashImage);
            myGameData.setGameName(name);
            activateSimmulate();
        }

        else {
            JOptionPane.showMessageDialog(null,
                                          "One or more fields invalid! Please try again.");
        }
    }

    private void activateSimmulate () {
        simulateButton.setEnabled(true);
    }

    public MouseAdapter setSplashImageListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    mySplashImage =
                            INPUT_CHOOSER
                                    .getSelectedFile()
                                    .toString()
                                    .replace(System.getProperties().getProperty("user.dir") + "/",
                                             "");
                    mySplashImageLabel.setText(mySplashImage);
                }

            }
        };
        return listener;

    }
}
