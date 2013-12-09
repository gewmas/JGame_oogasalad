package gameAuthoring.view;

import gameAuthoring.JSONObjects.AnimationJSONObject;
import gameAuthoring.JSONObjects.EnemyJSONObject;
import gameEngine.parser.Parser;
import java.awt.Color;
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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;


public class EnemyDesignTab extends Tab {

    private JScrollPane myCreatedEnemies;
    private JPanel myContentPanel;
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
    private JPanel myMainPanel;

    private final String[] SKILLS = { "Haste", "Armour", "Heal", "Light", "Poison" };

    private static final Dimension TEXT_DIMENSION = new Dimension(200, 30);
    private static final Dimension ANIMATION_BOX_DIMENSION = new Dimension(200, 100);
    private static final Dimension IMAGE_LABEL_DIMENSION = new Dimension(50, 50);
    private static final Dimension CONTENT_PANEL_DIMENSION = new Dimension(380, 400);
    private static final Dimension CREATED_ENEMIES_DIMENSION = new Dimension(380, 400);
    private static final Dimension ANIMATION_PANE_DIMENSION = new Dimension(200, 80);

    public EnemyDesignTab () {
    }

    @Override
    public JPanel getTab () {
        myMainPanel = new GradientPanel(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myMainPanel.setPreferredSize(StyleConstants.DEFAULT_PANEL_SIZE);
        addTitle();
        myScrollPanel = new JPanel(new MigLayout("wrap 4"));
        myScrollPanel.setOpaque(false);
        createMainPanel();
        myMainPanel.add(myContentPanel);
        myCreatedEnemies = new JScrollPane(myScrollPanel);
        myCreatedEnemies.getViewport().setOpaque(false);
        myCreatedEnemies.setOpaque(false);
        myCreatedEnemies.setPreferredSize(CREATED_ENEMIES_DIMENSION);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myCreatedEnemies.setBorder(BorderFactory
                .createTitledBorder(b,
                                    StyleConstants.resourceBundle.getString("EnemyCreatedEnemies"),
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    StyleConstants.TITLE_FONT_2));
        myMainPanel.add(myCreatedEnemies, "aligny center");
        return myMainPanel;
    }

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

    private void createMainPanel () {
        myContentPanel = new JPanel();
        addName();
        addLives();
        addGold();
        addSpeed();
        addDamage();
        addSkill();
        addAnimation();
        addCreateEnemyButton();
        myContentPanel.setLayout(new MigLayout("wrap 2"));
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myContentPanel.setPreferredSize(CONTENT_PANEL_DIMENSION);
        myContentPanel.setBorder(b);
        myContentPanel.setOpaque(false);
    }

    private void addTitle () {
        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("EnemyTitle"));
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, "span 2");
    }

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

    private void addSkill () {
        JLabel skill = new JLabel(StyleConstants.resourceBundle.getString("EnemySkill"));
        skill.setFont(StyleConstants.DEFAULT_BODY_FONT);
        skill.setToolTipText(StyleConstants.resourceBundle.getString("EnemySkillTip"));
        mySkillOptions = new JComboBox<String>(SKILLS);
        mySkillOptions.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(skill);
        myContentPanel.add(mySkillOptions);
    }


    private void addAnimation () {

        JButton enemyImageChooser =
                new JButton(StyleConstants.resourceBundle.getString("EnemySprite"));
        enemyImageChooser.setFont(StyleConstants.DEFAULT_BODY_FONT);
        enemyImageChooser.addMouseListener(createNewEnemyIconListener());
        enemyImageChooser
                .setToolTipText(StyleConstants.resourceBundle.getString("EnemySpriteTip"));

        JButton clearButton =
                new JButton(StyleConstants.resourceBundle.getString("EnemyClearSprites"));
        clearButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        clearButton.addMouseListener(createClearAnimationsListener());
        clearButton.setToolTipText(StyleConstants.resourceBundle.getString("EnemyClearSpritesTip"));
        myContentPanel.add(enemyImageChooser);
        myContentPanel.add(clearButton);

        myAnimationPanel = new JPanel();
        myAnimationPanel.setOpaque(false);
        myAnimationPanel.setPreferredSize(ANIMATION_BOX_DIMENSION);
        myAnimationScrollPane = new JScrollPane(myAnimationPanel);
        myAnimationScrollPane.setPreferredSize(ANIMATION_PANE_DIMENSION);
        myAnimationScrollPane.getViewport().setOpaque(false);
        myAnimationScrollPane.setOpaque(false);
        myAnimationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        myContentPanel.add(myAnimationScrollPane, "span 2, gap 0 0 10 10");

    }


    private void addCreateEnemyButton () {

        JButton createEnemyButton =
                new JButton(StyleConstants.resourceBundle.getString("EnemyCreation"));
        createEnemyButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        createEnemyButton.addMouseListener(createEnemyButtonListener());
        createEnemyButton
                .setToolTipText(StyleConstants.resourceBundle.getString("EnemyCreationTip"));
        myContentPanel.add(createEnemyButton);
    }

    private MouseAdapter createClearAnimationsListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                clearAnimationPanel();
            }
        };
        return listener;
    }

    private void clearAnimationPanel () {
        myAnimationPanel.removeAll();
        myAnimationPanel.revalidate();
        myEnemyAnimations.clear();
    }

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
                        myNameField.setText("");
                        myGoldField.setText("");
                        myLifeField.setText("");
                        mySpeedField.setText("");
                        myDamageField.setText("");
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
