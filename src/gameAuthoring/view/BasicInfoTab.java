package gameAuthoring.view;

import gameAuthoring.model.GameData;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca Lai & Susan Zhang
 *         BasicInfoTab allows for users to design the basic information for a Tower Defense game,
 *         including game name, starting amount of gold, starting number of lives, splash image,
 *         background audio, and alternate names for gold and lives.BasicInfoTab inherits from the
 *         Tab class and is thus an Observable. When all fields in BasicInfoTab are set,
 *         BasicInfoTab notifies its observers (i.e. controller) by passing a BasicInformation
 *         object. Its observers can then extract basic game information.
 */
public class BasicInfoTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/src/resources/img");
    private JTextField myGameName;
    private JTextField myGold;
    private JTextField myLives;
    private JTextField myAltGoldText;
    private JTextField myAltLivesText;
    private AudioLabel myAudioLabel;
    private ImageLabel myImageLabel;
    private static final Dimension TEXT_DIMENSION = new Dimension(200, 30);
    private static final Dimension IMAGE_LABEL_DIMENSION = new Dimension(50, 50);
    private static final String CONTENT_PANEL_FORMATTING = "align center";

    /**
     * Creates a new BasicInfoTab
     */
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
        addSubmitButton();
        addTitle();
        myMainPanel.add(myContentPanel, CONTENT_PANEL_FORMATTING);
        return myMainPanel;
    }

    /**
     * Adds button for submitting basic game information
     */
    private void addSubmitButton () {
        JButton setInfoButton =
                new JButton(StyleConstants.resourceBundle.getString("BasicInfoSubmit"));
        setInfoButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        setInfoButton
                .setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoSubmitTip"));
        setInfoButton.addMouseListener(setInfoListener());
        myContentPanel.add(setInfoButton);
    }

    /**
     * Adds title to BasicInfoTab
     */
    private void addTitle () {
        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoTitle"));
        title.setFont(StyleConstants.DEFAULT_BODY_FONT);
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, StyleConstants.DEFAULT_SPAN_MODE);
    }

    /**
     * Adds label and text field for setting game name
     */
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

    /**
     * Adds label and text field for setting starting amount of gold
     */
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

    /**
     * Adds label and text field for setting starting number of lives
     */
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

    /**
     * Adds label and AudioLabel for setting game's background audio
     */
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

    /**
     * Adds label and ImageLabel for setting bullet image
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see gameAuthoring.view.Tab#loadJSON(gameEngine.parser.Parser)
     */
    public void loadJSON (Parser p) {
        try {
            myGameName.setText(p.getString(GameData.GAME_NAME_KEY));
            myGold.setText(String.valueOf(p.getInt(GameData.GOLD_KEY)));
            myAltGoldText.setText(p.getString(GameData.GOLD_NAME_KEY));
            myLives.setText(String.valueOf(p.getInt(GameData.LIVES_KEY)));
            myAltLivesText.setText(p.getString(GameData.LIVES_NAME_KEY));
            JSONObject resources = p.getJSONObject(JSONConstants.RESOURCE_STRING);
            JSONArray images = (JSONArray) resources.get(JSONConstants.IMAGE_STRING);
            Map<String, String> imageMap = new HashMap<String, String>();
            for (int i = 0; i < images.length(); i++) {
                JSONObject image = images.getJSONObject(i);
                String id = image.getString(JSONConstants.ID_STRING);
                String url = image.getString(JSONConstants.URL_STRING);
                imageMap.put(id, url);
            }
            Map<String, String> audioMap = new HashMap<String, String>();
            JSONArray audio = (JSONArray) resources.get(JSONConstants.AUDIO_STRING);
            for (int i = 0; i < audio.length(); i++) {
                JSONObject sound = audio.getJSONObject(i);
                String id = sound.getString(JSONConstants.ID_STRING);
                String url = sound.getString(JSONConstants.URL_STRING);
                audioMap.put(id, url);
            }
            myImageLabel.setLabelIcon(new File(GameAuthoringGUI.FILE_PREFIX +
                                               imageMap.get(JSONConstants.BULLET_STRING)));
            myImageLabel.setID(JSONConstants.BULLET_KEY);
            String BGAudioID = (String) p.getString(JSONConstants.BG_AUDIO_STRING);
            myAudioLabel = new AudioLabel(BGAudioID, true);
            myAudioLabel.revalidate();
            myAudioLabel.setAudioFile(new File(GameAuthoringGUI.FILE_PREFIX +
                                               audioMap.get(BGAudioID)));
            setData();
        }
        catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null,
                                          "File invalid!");
        }
    }

    /**
     * @return MouseAdapter that sets all game basic information when mouse event occurs
     */
    private MouseAdapter setInfoListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                setData();
            }
        };
        return listener;
    }

    /**
     * @param audio is AudioLabel that stores audio file
     */
    public void setBackgroundAudio (AudioLabel audio) {
        myAudioLabel = audio;
    }

    /**
     * Extracts information from all text fields
     * Notifies observers of BasicInfoTab of changes
     * Sends all basic game information to observers
     */
    private void setData () {
        int gold = Integer.parseInt(myGold.getText());
        int lives = Integer.parseInt(myLives.getText());
        String name = myGameName.getText();
        if (myImageLabel.getImageFile() != null && myAudioLabel != null && gold > 0 &&
            lives > 0 &&
            name != null) {
            String bulletName = myImageLabel.getImageFile().getName();
            String goldName = myAltGoldText.getText();
            String livesName = myAltLivesText.getText();
            BasicInformation gameDesignInfo =
                    new BasicInformation(gold, lives, name, myAudioLabel,
                                         livesName, goldName, bulletName);
            setChanged();
            notifyObservers(gameDesignInfo);
            clearChanged();
            JOptionPane.showMessageDialog(null, StyleConstants.resourceBundle
                    .getString("BasicInfoValidSubmission"));
        }
        else {
            JOptionPane.showMessageDialog(null,
                                          StyleConstants.resourceBundle
                                                  .getString("BasicInfoInvalidSubmission"));
        }
    }
}
