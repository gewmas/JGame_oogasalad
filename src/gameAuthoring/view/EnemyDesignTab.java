package gameAuthoring.view;

import gameAuthoring.JSONObjects.AnimationJSONObject;
import gameAuthoring.JSONObjects.EnemyJSONObject;
import gameEngine.parser.Parser;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca Lai & Susan Zhang
 *         EnemyDesignTab allows for users to design parameters for game enemies, including their
 *         names, worth in gold, number of lives, damage amount, speed, and
 *         sprites/animations.EnemyDesignTab inherits from the Tab class and is thus an Observable.
 *         When all fields in EnemyDesignTab are set, it notifies its observers by sending an
 *         EnemyJSONObject. EnemyJSONObject is transferred from the observers (i.e. controllers) to
 *         GameData.
 */
public class EnemyDesignTab extends Tab {

    private JScrollPane myCreatedEnemies;
    private JPanel myScrollPanel;
    private JTextField myNameField;
    private JTextField myGoldField;
    private JTextField myLifeField;
    private JTextField myDamageField;
    private JTextField mySpeedField;
    private JPanel myAnimationPanel;
    private JScrollPane myAnimationScrollPane;
    private List<ImageLabel> myEnemyAnimations = new ArrayList<ImageLabel>();
    private File myImageSource;
    private int myNumEnemies = 0;
    private JComboBox<String> mySkillOptions;
    private final String[] SKILLS = { "Haste", "Armour", "Heal", "Light", "Poison" };
    private static final Dimension TEXT_DIMENSION = new Dimension(200, 30);
    private static final Dimension ANIMATION_BOX_DIMENSION = new Dimension(200, 100);
    private static final Dimension IMAGE_LABEL_DIMENSION = new Dimension(50, 50);
    private static final Dimension CONTENT_PANEL_DIMENSION = new Dimension(380, 400);
    private static final Dimension CREATED_ENEMIES_DIMENSION = new Dimension(380, 400);
    private static final Dimension ANIMATION_PANE_DIMENSION = new Dimension(200, 80);
    private static final String ANIMATION_SCROLL_PANE_FORMATTING = "gap 0 0 10 10";
    private static final String SCROLL_PANEL_WRAP_MODE = "wrap 4";
    private static final String CREATED_ENEMIES_PANEL_FORMATTING = "aligny center";

    /**
     * Creates new EnemyDesignTab
     */
    public EnemyDesignTab () {
    }

    @Override
    public JPanel getTab () {
        myMainPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myMainPanel.setPreferredSize(StyleConstants.DEFAULT_PANEL_SIZE);
        addTitle();
        myScrollPanel = new JPanel(new MigLayout(SCROLL_PANEL_WRAP_MODE));
        myScrollPanel.setOpaque(false);
        createMainPanel();
        myMainPanel.add(myContentPanel);
        myCreatedEnemies = new JScrollPane(myScrollPanel);
        myCreatedEnemies.getViewport().setOpaque(false);
        myCreatedEnemies.setOpaque(false);
        myCreatedEnemies.setPreferredSize(CREATED_ENEMIES_DIMENSION);
        myCreatedEnemies.setBorder(BorderFactory
                .createTitledBorder(StyleConstants.DEFAULT_PANEL_BORDER,
                                    StyleConstants.resourceBundle.getString("EnemyCreatedEnemies"),
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    StyleConstants.TITLE_FONT_2));
        myMainPanel.add(myCreatedEnemies, CREATED_ENEMIES_PANEL_FORMATTING);
        return myMainPanel;
    }

    /**
     * Displays created enemy in CreatedEnemies panel
     * 
     * @param imgSource is enemy's image/sprites
     * @param enemyName is name of enemy
     */
    private void addEnemy (File imgSource, String enemyName) {
        JLabel enemyIcon = new JLabel();
        enemyIcon.setPreferredSize(IMAGE_LABEL_DIMENSION);
        try {
            Image enemyImage = ImageIO.read(imgSource);
            enemyIcon.setIcon(new ImageIcon(enemyImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        JLabel enemyNameLabel = new JLabel(enemyName);
        enemyNameLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myScrollPanel.add(enemyNameLabel);
        myScrollPanel.add(enemyIcon);
    }

    /**
     * Sets all fields in the main panel
     */
    private void createMainPanel () {
        myContentPanel = new JPanel();
        addName();
        addGold();
        addLives();
        addSpeed();
        addDamage();
        addSkill();
        addAnimation();
        addEnemySpriteChooser();
        addClearButton();
        addCreateEnemyButton();
        myContentPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myContentPanel.setPreferredSize(CONTENT_PANEL_DIMENSION);
        myContentPanel.setBorder(StyleConstants.DEFAULT_PANEL_BORDER);
        myContentPanel.setOpaque(false);
    }

    /**
     * Adds descriptive title to main panel
     */
    private void addTitle () {
        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("EnemyTitle"));
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, "span 2");
    }

    /**
     * Adds label and text field that allows setting enemy name
     */
    private void addName () {
        JLabel name = new JLabel(StyleConstants.resourceBundle.getString("EnemyName"));
        name.setFont(StyleConstants.DEFAULT_BODY_FONT);
        name.setToolTipText(StyleConstants.resourceBundle.getString("EnemyNameTip"));
        myNameField = new JTextField();
        myNameField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myNameField.setPreferredSize(TEXT_DIMENSION);
        myContentPanel.add(name);
        myContentPanel.add(myNameField);
    }

    /**
     * Adds label and text field that allows setting enemy's worth in gold
     */
    private void addGold () {
        JLabel gold = new JLabel(StyleConstants.resourceBundle.getString("EnemyGold"));
        gold.setFont(StyleConstants.DEFAULT_BODY_FONT);
        gold.setToolTipText(StyleConstants.resourceBundle.getString("EnemyGoldTip"));
        myGoldField = new JTextField();
        myGoldField.setPreferredSize(TEXT_DIMENSION);
        myGoldField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(gold);
        myContentPanel.add(myGoldField);
    }

    /**
     * Adds label and text field that allows setting enemy's number of lives
     */
    private void addLives () {
        JLabel lives = new JLabel(StyleConstants.resourceBundle.getString("EnemyLives"));
        lives.setFont(StyleConstants.DEFAULT_BODY_FONT);
        lives.setToolTipText(StyleConstants.resourceBundle.getString("EnemyLivesTip"));
        myLifeField = new JTextField();
        myLifeField.setPreferredSize(TEXT_DIMENSION);
        myLifeField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(lives);
        myContentPanel.add(myLifeField);

    }

    /**
     * Adds label and text field that allows setting enemy speed
     */
    private void addSpeed () {
        JLabel speed = new JLabel(StyleConstants.resourceBundle.getString("EnemySpeed"));
        speed.setFont(StyleConstants.DEFAULT_BODY_FONT);
        speed.setToolTipText(StyleConstants.resourceBundle.getString("EnemySpeedTip"));
        mySpeedField = new JTextField();
        mySpeedField.setPreferredSize(TEXT_DIMENSION);
        mySpeedField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(speed);
        myContentPanel.add(mySpeedField);
    }

    /**
     * Adds label and text field that allows setting amount of damage that enemy inflicts
     */
    private void addDamage () {
        JLabel damage = new JLabel(StyleConstants.resourceBundle.getString("EnemyDamage"));
        damage.setFont(StyleConstants.DEFAULT_BODY_FONT);
        damage.setToolTipText(StyleConstants.resourceBundle.getString("EnemyDamageTip"));
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(TEXT_DIMENSION);
        myDamageField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(damage);
        myContentPanel.add(myDamageField);

    }

    /**
     * Adds label and text field that allows setting enemy's special skill type
     */
    private void addSkill () {
        JLabel skill = new JLabel(StyleConstants.resourceBundle.getString("EnemySkill"));
        skill.setFont(StyleConstants.DEFAULT_BODY_FONT);
        skill.setToolTipText(StyleConstants.resourceBundle.getString("EnemySkillTip"));
        mySkillOptions = new JComboBox<String>(SKILLS);
        mySkillOptions.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(skill);
        myContentPanel.add(mySkillOptions);
    }

    /**
     * Adds label and scroll pane that allows setting sprites for enemy
     */
    private void addAnimation () {
        JLabel animation = new JLabel(StyleConstants.resourceBundle.getString("EnemyAnimation"));
        animation.setFont(StyleConstants.DEFAULT_BODY_FONT);
        animation.setToolTipText(StyleConstants.resourceBundle.getString("EnemyAnimationTip"));
        myAnimationPanel = new JPanel();
        myAnimationPanel.setOpaque(false);
        myAnimationPanel.setPreferredSize(ANIMATION_BOX_DIMENSION);
        myAnimationScrollPane = new JScrollPane(myAnimationPanel);
        myAnimationScrollPane.setPreferredSize(ANIMATION_PANE_DIMENSION);
        myAnimationScrollPane.getViewport().setOpaque(false);
        myAnimationScrollPane.setOpaque(false);
        myAnimationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        myContentPanel.add(animation);
        myContentPanel.add(myAnimationScrollPane, ANIMATION_SCROLL_PANE_FORMATTING);
    }

    /**
     * Adds button for adding another sprite box to animation scroll pane
     */
    public void addEnemySpriteChooser () {
        JButton enemyImageChooser =
                new JButton(StyleConstants.resourceBundle.getString("EnemySprite"));
        enemyImageChooser.setFont(StyleConstants.DEFAULT_BODY_FONT);
        enemyImageChooser.addMouseListener(createNewEnemyIconListener());
        enemyImageChooser
                .setToolTipText(StyleConstants.resourceBundle.getString("EnemySpriteTip"));
        myContentPanel.add(enemyImageChooser);
    }

    /**
     * Adds button that allows for clearing of all sprites uploaded so far for enemy
     */
    public void addClearButton () {
        JButton clearButton =
                new JButton(StyleConstants.resourceBundle.getString("EnemyClearSprites"));
        clearButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        clearButton.setToolTipText(StyleConstants.resourceBundle.getString("EnemyClearSpritesTip"));
        clearButton.addMouseListener(createClearAnimationsListener());
        clearButton.setToolTipText(StyleConstants.resourceBundle.getString("EnemyClearSpritesTip"));
        myContentPanel.add(clearButton);
    }

    /**
     * Adds button that completes creation of enemy
     */
    private void addCreateEnemyButton () {
        JButton createEnemyButton =
                new JButton(StyleConstants.resourceBundle.getString("EnemyCreation"));
        createEnemyButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        createEnemyButton.setToolTipText(StyleConstants.resourceBundle
                .getString("EnemyCreationTip"));
        createEnemyButton.addMouseListener(createEnemyButtonListener());
        createEnemyButton
                .setToolTipText(StyleConstants.resourceBundle.getString("EnemyCreationTip"));
        myContentPanel.add(createEnemyButton);
    }

    /**
     * Clears animations in scroll pane upon click
     * 
     * @return MouseAdapter that allows for clearing of animations in animation scroll pane
     */
    private MouseAdapter createClearAnimationsListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                clearAnimationPanel();
            }
        };
        return listener;
    }

    /**
     * Clears animation pane of all objects
     */
    private void clearAnimationPanel () {
        myAnimationPanel.removeAll();
        myAnimationPanel.revalidate();
        myEnemyAnimations.clear();
    }

    /**
     * Displays newly created enemies in tab
     * 
     * @return MouseAdapter that allows for newly created enemy to be displayed in CreatedEnemies
     *         panel
     */
    private MouseAdapter createNewEnemyIconListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                ImageLabel enemyImage = new ImageLabel();
                enemyImage.setSize(IMAGE_LABEL_DIMENSION);
                enemyImage.setMutableStatusTrue();
                myAnimationPanel.add(enemyImage);
                myEnemyAnimations.add(enemyImage);
                myAnimationPanel.revalidate();
            }
        };
        return listener;
    }

    /**
     * Creates new enemy from completed fields in tab
     * Notifies observers of change
     * Sends EnemyJSONObject to observer
     * 
     * @return MouseAdapter that allows for enemy to be created and passed to GameData
     */
    private MouseAdapter createEnemyButtonListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                myNumEnemies++;
                String currentEnemyID = "enemy" + Integer.toString(myNumEnemies);
                try {
                    int gold = Integer.parseInt(myGoldField.getText());
                    int life = Integer.parseInt(myLifeField.getText());
                    int damage = Integer.parseInt(myGoldField.getText());
                    double speed = Double.parseDouble(mySpeedField.getText());
                    String skill = (String) mySkillOptions.getSelectedItem();
                    myImageSource = myEnemyAnimations.get(0).getImageFile();
                    List<String> enemyAnimationPaths = new ArrayList<String>();
                    if (myEnemyAnimations.size() > 1) {
                        for (ImageLabel imageLabel : myEnemyAnimations) {
                            enemyAnimationPaths.add(imageLabel.getID());
                        }
                        AnimationJSONObject animation =
                                new AnimationJSONObject(currentEnemyID, enemyAnimationPaths);
                        setChanged();
                        notifyObservers(animation);
                        clearChanged();
                    }
                    else {
                        currentEnemyID = myEnemyAnimations.get(0).getID();
                    }
                    if (gold < 0 || life < 0) {
                        JOptionPane
                                .showMessageDialog(null,
                                                   StyleConstants.resourceBundle
                                                           .getString("EnemyNegativeInput"));
                    }
                    else {
                        EnemyJSONObject enemy =
                                new EnemyJSONObject(myNameField.getText(), gold, currentEnemyID,
                                                    life, damage, speed, skill);
                        setChanged();
                        notifyObservers(enemy);
                        clearChanged();
                        addEnemy(myImageSource, myNameField.getText());
                        myNameField.setText(StyleConstants.NULL_STRING);
                        myGoldField.setText(StyleConstants.NULL_STRING);
                        myLifeField.setText(StyleConstants.NULL_STRING);
                        mySpeedField.setText(StyleConstants.NULL_STRING);
                        myDamageField.setText(StyleConstants.NULL_STRING);
                        clearAnimationPanel();
                    }
                }
                catch (NumberFormatException n)
                {
                    JOptionPane.showMessageDialog(null, StyleConstants.resourceBundle
                            .getString("EnemyInvalidInput"));
                }
            }
        };
        return listener;
    }

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub

    }

}
