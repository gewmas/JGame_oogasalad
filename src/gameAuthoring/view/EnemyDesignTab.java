package gameAuthoring.view;

import gameAuthoring.JSONObjects.AnimationJSONObject;
import gameAuthoring.JSONObjects.EnemyJSONObject;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    private JPanel myMainPanel;
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

    public EnemyDesignTab () {
    }

    @Override
    public JPanel getTab () {
        myMainPanel = new GradientPanel(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myMainPanel.setPreferredSize(StyleConstants.DEFAULT_PANEL_SIZE);
        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("EnemyDesignTitle"));
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        title.setForeground(new Color(80, 80, 80));
        myMainPanel.add(title, "span 2");
        myScrollPanel = new JPanel(new MigLayout("wrap 4"));
        myScrollPanel.setOpaque(false);
        createMainPanel();
        myMainPanel.add(myContentPanel);
        myCreatedEnemies = new JScrollPane(myScrollPanel);
        myCreatedEnemies.getViewport().setOpaque(false);
        myCreatedEnemies.setOpaque(false);
        myCreatedEnemies.setPreferredSize(new Dimension(380, 400));
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myCreatedEnemies.setBorder(BorderFactory
                .createTitledBorder(b, StyleConstants.resourceBundle.getString("EnemyCreatedEnemies"),
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    new Font("Calibri", Font.PLAIN, 20)));
        myMainPanel.add(myCreatedEnemies, "aligny center");
        return myMainPanel;
    }

    private void addEnemy (File imgSource, String enemyName) {
        JLabel enemyIcon = new JLabel();
        enemyIcon.setPreferredSize(new Dimension(50, 50));
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
        JLabel name = new JLabel(StyleConstants.resourceBundle.getString("EnemyName"));
        name.setFont(StyleConstants.DEFAULT_BODY_FONT);
        name.setToolTipText(StyleConstants.resourceBundle.getString("EnemyNameTip"));

        JLabel gold = new JLabel(StyleConstants.resourceBundle.getString("EnemyGold"));
        gold.setFont(StyleConstants.DEFAULT_BODY_FONT);
        gold.setToolTipText(StyleConstants.resourceBundle.getString("EnemyGoldTip"));

        JLabel lives = new JLabel(StyleConstants.resourceBundle.getString("EnemyLives"));
        lives.setFont(StyleConstants.DEFAULT_BODY_FONT);
        lives.setToolTipText(StyleConstants.resourceBundle.getString("EnemyLivesTip"));

        JLabel speed = new JLabel(StyleConstants.resourceBundle.getString("EnemySpeed"));
        speed.setFont(StyleConstants.DEFAULT_BODY_FONT);
        speed.setToolTipText(StyleConstants.resourceBundle.getString("EnemySpeedTip"));

        JLabel skill = new JLabel(StyleConstants.resourceBundle.getString("EnemySkill"));
        skill.setFont(StyleConstants.DEFAULT_BODY_FONT);
        skill.setToolTipText(StyleConstants.resourceBundle.getString("EnemySkillTip"));

        JLabel damage = new JLabel(StyleConstants.resourceBundle.getString("EnemyDamage"));
        damage.setFont(StyleConstants.DEFAULT_BODY_FONT);
        damage.setToolTipText(StyleConstants.resourceBundle.getString("EnemyDamageTip"));

        myNameField = new JTextField();
        myNameField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myNameField.setPreferredSize(new Dimension(200, 30));

        myGoldField = new JTextField();
        myGoldField.setPreferredSize(new Dimension(200, 30));
        myGoldField.setFont(StyleConstants.DEFAULT_BODY_FONT);

        myLifeField = new JTextField();
        myLifeField.setPreferredSize(new Dimension(200, 30));
        myLifeField.setFont(StyleConstants.DEFAULT_BODY_FONT);

        mySpeedField = new JTextField();
        mySpeedField.setPreferredSize(new Dimension(200, 30));
        mySpeedField.setFont(StyleConstants.DEFAULT_BODY_FONT);

        myDamageField = new JTextField();
        myDamageField.setPreferredSize(new Dimension(200, 30));
        myDamageField.setFont(StyleConstants.DEFAULT_BODY_FONT);

        JButton enemyImageChooser = new JButton(StyleConstants.resourceBundle.getString("EnemySprite"));
        enemyImageChooser.setFont(StyleConstants.DEFAULT_BODY_FONT);
        enemyImageChooser.addMouseListener(createNewEnemyIconListener());
        enemyImageChooser
                .setToolTipText(StyleConstants.resourceBundle.getString("EnemySpriteTip"));

        JButton clearButton = new JButton(StyleConstants.resourceBundle.getString("EnemyClearSprites"));
        clearButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        clearButton.addMouseListener(createClearAnimationsListener());
        clearButton.setToolTipText(StyleConstants.resourceBundle.getString("EnemyClearSpritesTip"));

        mySkillOptions = new JComboBox<String>(SKILLS);
        mySkillOptions.setFont(StyleConstants.DEFAULT_BODY_FONT);

        myAnimationPanel = new JPanel();
        myAnimationPanel.setOpaque(false);
        myAnimationPanel.setPreferredSize(new Dimension(200, 100));
        myAnimationScrollPane = new JScrollPane(myAnimationPanel);
        myAnimationScrollPane.setPreferredSize(new Dimension(200, 80));
        myAnimationScrollPane.getViewport().setOpaque(false);
        myAnimationScrollPane.setOpaque(false);
        myAnimationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JButton createEnemyButton = new JButton(StyleConstants.resourceBundle.getString("EnemyCreateEnemy"));
        createEnemyButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        createEnemyButton.addMouseListener(createEnemyButtonListener());
        createEnemyButton
                .setToolTipText(StyleConstants.resourceBundle.getString("EnemyCreateEnemyTip"));

        myContentPanel.setLayout(new MigLayout("wrap 2"));
        myContentPanel.add(name);
        myContentPanel.add(myNameField);
        myContentPanel.add(gold);
        myContentPanel.add(myGoldField);
        myContentPanel.add(lives);
        myContentPanel.add(myLifeField);
        myContentPanel.add(speed);
        myContentPanel.add(mySpeedField);
        myContentPanel.add(damage);
        myContentPanel.add(myDamageField);
        myContentPanel.add(skill);
        myContentPanel.add(mySkillOptions);
        myContentPanel.add(myAnimationScrollPane, "span 2, gap 0 0 10 10");
        myContentPanel.add(enemyImageChooser);
        myContentPanel.add(clearButton);
        myContentPanel.add(createEnemyButton);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myContentPanel.setPreferredSize(new Dimension(380, 400));
        myContentPanel.setBorder(b);
        myContentPanel.setOpaque(false);
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
                ImageLabel enemyImage = new ImageLabel(50, 50);
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
                                                   StyleConstants.resourceBundle.getString("EnemyNegativeInput"));
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
                    JOptionPane.showMessageDialog(null, StyleConstants.resourceBundle.getString("EnemyInvalidInput"));
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
