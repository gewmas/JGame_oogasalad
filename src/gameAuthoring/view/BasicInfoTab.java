package gameAuthoring.view;

import gameAuthoring.JSONObjects.ResourceJSONObject;
import gameEngine.parser.Parser;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


public class BasicInfoTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/src/resources/img");
    private JTextField myGameName;
    private JTextField myGold;
    private JTextField myLives;
    private JTextField myAltGoldText;
    private JTextField myAltLivesText;
    private JLabel mySplashImageLabel;
    private String mySplashImage;
    private AudioLabel myAudioLabel;
    private ImageLabel myImageLabel;
    private static final Dimension TEXT_DIMENSION = new Dimension(200, 30);
    private static final Dimension IMAGE_LABEL_DIMENSION = new Dimension(50, 50);
    private static final String CONTENT_PANEL_FORMATTING = "align center";

    public BasicInfoTab () {

    }

    @Override
    public JPanel getTab () {
        myMainPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myContentPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myContentPanel.setOpaque(false);
        myContentPanel.setBorder(StyleConstants.DEFAULT_PANEL_BORDER);
        addGameName();
        addGold();
        addLives();
        addBackgroundAudio();
        addBullet();
        addSplashImage();
        addSubmitButton();
        addTitle();
        myMainPanel.add(myContentPanel, CONTENT_PANEL_FORMATTING);
        return myMainPanel;
    }

    private void addSubmitButton () {
        JButton setInfoButton =
                new JButton(StyleConstants.resourceBundle.getString("BasicInfoSubmit"));
        setInfoButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        setInfoButton
                .setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoSubmitTip"));
        setInfoButton.addMouseListener(setInfoListener());
        myContentPanel.add(setInfoButton);
    }

    private void addTitle () {
        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoTitle"));
        title.setFont(StyleConstants.DEFAULT_BODY_FONT);
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, StyleConstants.DEFAULT_SPAN_MODE);
    }

    private void addGameName () {
        JLabel gameName = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoGameName"));
        gameName.setFont(StyleConstants.DEFAULT_BODY_FONT);
        gameName.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoGameNameTip"));
        myGameName = new JTextField();
        myGameName.setPreferredSize(TEXT_DIMENSION);
        myGameName.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(gameName);
        myContentPanel.add(myGameName);

    }

    private void addGold () {
        JLabel gold = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoGold"));
        gold.setFont(StyleConstants.DEFAULT_BODY_FONT);
        gold.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoGoldTip"));
        myGold = new JTextField();
        myGold.setPreferredSize(TEXT_DIMENSION);
        JLabel altGoldLabel =
                new JLabel(StyleConstants.resourceBundle.getString("BasicInfoAltGold"));
        altGoldLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        altGoldLabel.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoAltGoldTip"));
        myAltGoldText = new JTextField();
        myAltGoldText.setPreferredSize(TEXT_DIMENSION);
        myAltGoldText.setFont(StyleConstants.DEFAULT_BODY_FONT);

        myContentPanel.add(gold);
        myContentPanel.add(myGold);
        myContentPanel.add(altGoldLabel);
        myContentPanel.add(myAltGoldText);
    }

    private void addLives () {
        JLabel lives = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoLives"));
        lives.setFont(StyleConstants.DEFAULT_BODY_FONT);
        lives.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoLivesTip"));
        myLives = new JTextField();
        myLives.setPreferredSize(TEXT_DIMENSION);
        JLabel altLivesLabel = new JLabel("Alternative Lives Name:");
        altLivesLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        altLivesLabel.setToolTipText("If you want, choose another name for your lives.");
        myAltLivesText = new JTextField();
        myAltLivesText.setPreferredSize(TEXT_DIMENSION);
        myAltLivesText.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(lives);
        myContentPanel.add(myLives);
        myContentPanel.add(altLivesLabel);
        myContentPanel.add(myAltLivesText);
    }

    private void addBackgroundAudio () {
        myAudioLabel = new AudioLabel();
        JLabel audioLabel =
                new JLabel(StyleConstants.resourceBundle.getString("BasicInfoBackgroundAudio"));
        audioLabel.setToolTipText(StyleConstants.resourceBundle
                .getString("BasicInfoBackgroundAudioTip"));
        myAudioLabel.setMutableStatusTrue();
        audioLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(audioLabel);
        myContentPanel.add(myAudioLabel);
    }

    private void addBullet () {
        JLabel bullet = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoBullet"));
        bullet.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoBulletTip"));
        bullet.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myImageLabel = new ImageLabel();
        myImageLabel.setMutableStatusTrue();
        myImageLabel.setPreferredSize(IMAGE_LABEL_DIMENSION);
        myContentPanel.add(bullet);
        myContentPanel.add(myImageLabel);
    }

    private void addSplashImage () {
        JButton setSplashImageButton =
                new JButton(StyleConstants.resourceBundle.getString("BasicInfoSplashImage"));
        setSplashImageButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        setSplashImageButton
                .setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoSplashImageTip"));
        setSplashImageButton.addMouseListener(setSplashImageListener());
        mySplashImageLabel = new JLabel();
        myContentPanel.add(setSplashImageButton);
        myContentPanel.add(mySplashImageLabel);
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

    private MouseAdapter setInfoListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                setData();
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
        if (myAudioLabel != null && gold > 0 && lives > 0 && mySplashImage != null &&
            name != null && bulletName != null) {
            String goldName = myAltGoldText.getText();
            String livesName = myAltLivesText.getText();
            String splashImage = mySplashImage.substring(0, mySplashImage.length() - 4);
            BasicInformation gameDesignInfo =
                    new BasicInformation(gold, lives, splashImage, name, myAudioLabel,
                                         livesName, goldName, bulletName);
            setChanged();
            notifyObservers(gameDesignInfo);
            clearChanged();
        }

        else {
            JOptionPane.showMessageDialog(null,
                                          StyleConstants.resourceBundle
                                                  .getString("BasicInfoInvalidSubmission"));
        }
    }

    private MouseAdapter setSplashImageListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    mySplashImage = INPUT_CHOOSER.getSelectedFile().getName();
                    ResourceJSONObject splashImage =
                            new ResourceJSONObject(
                                                   mySplashImage.substring(0,
                                                                           mySplashImage.length() - 4),
                                                   mySplashImage);
                    setChanged();
                    notifyObservers(splashImage);
                    clearChanged();
                    mySplashImageLabel.setText(mySplashImage);
                }

            }
        };
        return listener;
    }
}
