package gameAuthoring.view;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.menuBar.Simulator;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
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
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/src/resources/img");

    private JTextField myGameName;
    private JTextField myGold;
    private JTextField myLives;
    private JTextField altGoldText;
    private JTextField altLivesText;

    private JLabel mySplashImageLabel;
    private String mySplashImage;
    private JButton simulateButton;

    private AudioLabel myAudioLabel;
    private ImageLabel myImageLabel;

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
        gameName.setFont(Constants.DEFAULT_BODY_FONT);
        gameName.setToolTipText("Give your game a name. This will be displayed on the game start page.");

        JLabel gold = new JLabel("Starting Gold");
        gold.setFont(Constants.DEFAULT_BODY_FONT);
        gold.setToolTipText("Input the number of gold a player starts out with.");

        JLabel lives = new JLabel("Starting Lives");
        lives.setFont(Constants.DEFAULT_BODY_FONT);
        lives.setToolTipText("Input the number of lives a player starts out with.");

        JLabel title = new JLabel("Basic Game Info");
        title.setFont(Constants.DEFAULT_BODY_FONT);
        
        JLabel bullet = new JLabel("Bullet Image");
        bullet.setFont(Constants.DEFAULT_BODY_FONT);
        
        myImageLabel = new ImageLabel();
        myImageLabel.setMutableStatusTrue();
        myImageLabel.setPreferredSize(new Dimension(50, 50));
        Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
        myImageLabel.setBorder(border);

        title.setFont(Constants.DEFAULT_TITLE_FONT);
        title.setForeground(new Color(80, 80, 80));
        mainPanel.add(title, "span 2");

        JButton setSplashImageButton = new JButton("Choose Splash Image");
        setSplashImageButton.setFont(Constants.DEFAULT_BODY_FONT);
        setSplashImageButton
                .setToolTipText("Select a Splash Image to be displayed before your game begins.");
        setSplashImageButton.addMouseListener(setSplashImageListener());

        mySplashImageLabel = new JLabel();

        JButton setInfoButton = new JButton("Set Info");
        simulateButton = new JButton("Simulate");
        simulateButton.setEnabled(false);
        simulateButton.setFont(Constants.DEFAULT_BODY_FONT);
        simulateButton.addMouseListener(setSimulateListener());
        setInfoButton.setFont(Constants.DEFAULT_BODY_FONT);
        setInfoButton
                .setToolTipText("Once all fields have been completed, click the submit button. You can then proceed to 'preview' your game.");
        setInfoButton.addMouseListener(setInfoListener());

        myGameName = new JTextField();
        myGameName.setPreferredSize(new Dimension(200, 30));
        myGold = new JTextField();
        JLabel altGoldLabel = new JLabel("Alternative Gold Name:");
        altGoldLabel.setFont(Constants.DEFAULT_BODY_FONT);
        altGoldLabel.setToolTipText("If you want, choose another name for your currency.");
        altGoldText = new JTextField();
        altGoldText.setPreferredSize(new Dimension(200, 30));
        altGoldText.setFont(Constants.DEFAULT_BODY_FONT);

        myGold.setPreferredSize(new Dimension(200, 30));
        myLives = new JTextField();
        myLives.setPreferredSize(new Dimension(200, 30));
        JLabel altLivesLabel = new JLabel("Alternative Lives Name:");
        altLivesLabel.setFont(Constants.DEFAULT_BODY_FONT);
        altLivesLabel.setToolTipText("If you want, choose another name for your lives.");
        altLivesText = new JTextField();
        altLivesText.setPreferredSize(new Dimension(200, 30));
        altLivesText.setFont(Constants.DEFAULT_BODY_FONT);

        myAudioLabel = new AudioLabel();
        JLabel audioLabel = new JLabel("Background Audio:");
        audioLabel.setToolTipText("Select an audio to be played in the background of your game");
        myAudioLabel.setMutableStatusTrue();
        audioLabel.setFont(Constants.DEFAULT_BODY_FONT);

        subPanel.add(gameName);
        subPanel.add(myGameName);
        subPanel.add(gold);
        subPanel.add(myGold);
        subPanel.add(altGoldLabel);
        subPanel.add(altGoldText);
        subPanel.add(lives);
        subPanel.add(myLives);
        subPanel.add(altLivesLabel);
        subPanel.add(altLivesText);
        subPanel.add(audioLabel);
        subPanel.add(myAudioLabel);
        subPanel.add(bullet);
        subPanel.add(myImageLabel);
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

    public void setBackgroundAudio (AudioLabel audio) {
        myAudioLabel = audio;
    }

    private void setData () {
        int gold = Integer.parseInt(myGold.getText());
        int lives = Integer.parseInt(myLives.getText());
        String name = myGameName.getText();
        String bulletName = myImageLabel.getImageFile().getName();
        if (myAudioLabel != null && gold > 0 && lives > 0 && mySplashImage != null && name != null && bulletName != null) {
            String goldName = altGoldText.getText();
            String livesName = altLivesText.getText();
            myGameData.setGold(gold);
            myGameData.setLives(lives);
            myGameData.setSplashImage(mySplashImage.substring(0, mySplashImage.length()-4));
            myGameData.setGameName(name);
            myGameData.addAudio(myAudioLabel.getID(), myAudioLabel.getAudioFile().getName());
            myGameData.setGoldName(goldName);
            myGameData.setLivesName(livesName);
            myGameData.addImage("bullet", bulletName);
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
             
                    mySplashImage = INPUT_CHOOSER.getSelectedFile().getName();
                    myGameData.addImage(mySplashImage.substring(0, mySplashImage.length()-4), mySplashImage);
                    mySplashImageLabel.setText(mySplashImage);
                }

            }
        };
        return listener;

    }
}