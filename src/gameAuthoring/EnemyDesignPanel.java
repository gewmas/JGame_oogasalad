package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


public class EnemyDesignPanel extends JPanel {

    private EnemyDesignTab myEnemyDesignTab;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/img");

    private JTextField myNameField;
    private JTextField myGoldField;
    private JTextField myLifeField;
    private JTextField mySpeedField;
    private JPanel myAnimationPanel;
    private JScrollPane myAnimationScrollPane;
    private JLabel myEnemyImage;
    private List<ImageLabel> myEnemyAnimations = new ArrayList<ImageLabel>();
    private File myImageSource;
    private int myNumEnemies = 0;
    private JComboBox<String> mySkillOptions;
    
    private final String[] SKILLS = {"", "Haste", "Armour", "Heal", "Light", "Poison"};

    public EnemyDesignPanel (EnemyDesignTab enemyDesignTab) {
        myEnemyDesignTab = enemyDesignTab;
        JLabel name = new JLabel("Name");
        name.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel gold = new JLabel("Worth in Gold");
        gold.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel lives = new JLabel("Number of Lives");
        lives.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel speed = new JLabel("Speed");
        speed.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel skill = new JLabel("Skill");
        skill.setFont(Constants.DEFAULT_BODY_FONT);

        myNameField = new JTextField();
        myNameField.setFont(Constants.DEFAULT_BODY_FONT);
        myNameField.setPreferredSize(new Dimension(200, 30));

        myGoldField = new JTextField();
        myGoldField.setPreferredSize(new Dimension(200, 30));
        myGoldField.setFont(Constants.DEFAULT_BODY_FONT);

        myLifeField = new JTextField();
        myLifeField.setPreferredSize(new Dimension(200, 30));
        myLifeField.setFont(Constants.DEFAULT_BODY_FONT);

        mySpeedField = new JTextField();
        mySpeedField.setPreferredSize(new Dimension(200, 30));
        mySpeedField.setFont(Constants.DEFAULT_BODY_FONT);

        JLabel enemySoundLabel = new JLabel("Enemy attack sound:");
        enemySoundLabel.setFont(Constants.DEFAULT_BODY_FONT);
        AudioLabel enemyAudio = new AudioLabel();

        JButton enemyImageChooser = new JButton("Add sprite");
        enemyImageChooser.setFont(Constants.DEFAULT_BODY_FONT);
        enemyImageChooser.addMouseListener(createNewEnemyIconListener());

        JButton clearButton = new JButton("Clear all sprites");
        clearButton.setFont(Constants.DEFAULT_BODY_FONT);
        clearButton.addMouseListener(createClearAnimationsListener());

        mySkillOptions = new JComboBox<String>(SKILLS);
        
        myAnimationPanel = new JPanel();
        myAnimationPanel.setOpaque(false);
        myAnimationPanel.setPreferredSize(new Dimension(200, 100));
        JScrollPane animationScrollPane = new JScrollPane(myAnimationPanel);
        animationScrollPane.setPreferredSize(new Dimension(200, 80));
        animationScrollPane.getViewport().setOpaque(false);
        animationScrollPane.setOpaque(false);
        animationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JButton createEnemyButton = new JButton("Create Enemy");
        createEnemyButton.setFont(Constants.DEFAULT_BODY_FONT);
        createEnemyButton.addMouseListener(createEnemyButtonListener());

        this.setLayout(new MigLayout("wrap 2"));
        this.add(name);
        this.add(myNameField);
        this.add(gold);
        this.add(myGoldField);
        this.add(lives);
        this.add(myLifeField);
        this.add(speed);
        this.add(mySpeedField);
        this.add(skill);
        this.add(mySkillOptions);
        this.add(animationScrollPane, "span 2, gap 0 0 10 10");
        this.add(enemyImageChooser);
        this.add(clearButton);
        this.add(enemySoundLabel);
        this.add(enemyAudio);
        this.add(createEnemyButton);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        this.setPreferredSize(new Dimension(380, 350));
        this.setBorder(b);
        this.setOpaque(false);
    }

    public MouseAdapter createClearAnimationsListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                clearAnimationPanel();
            }
        };
        return listener;
    }

    public void clearAnimationPanel () {
        myAnimationPanel.removeAll();
        myAnimationPanel.revalidate();
    }

    public MouseAdapter createNewEnemyIconListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                ImageLabel enemyImage = new ImageLabel(50, 50);
                myAnimationPanel.add(enemyImage);
                myEnemyAnimations.add(enemyImage);
                myAnimationPanel.validate();
            }
        };
        return listener;
    }

    public MouseAdapter createEnemyButtonListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                GameData gameData = myEnemyDesignTab.getGameData();
                String currentEnemyID = "enemy" + Integer.toString(myNumEnemies);
                try {
                    int gold = Integer.parseInt(myGoldField.getText());
                    int life = Integer.parseInt(myLifeField.getText());
                    double speed = Double.parseDouble(mySpeedField.getText());
                    String skill = (String) mySkillOptions.getSelectedItem();
                    myImageSource = myEnemyAnimations.get(0).getImageFile();
                    List<String> enemyAnimationPaths = new ArrayList<String>();
                    for (ImageLabel imageLabel : myEnemyAnimations) {
                        enemyAnimationPaths.add(imageLabel.getID());
                        gameData.addAnimation(currentEnemyID,
                                              enemyAnimationPaths);
                    }
                    if (gold < 0 || life < 0) {
                        JOptionPane
                                .showMessageDialog(null,
                                                   "Cannot have negative values for gold, life, or speed");
                    }
                    else {
                        gameData
                                .addEnemy(myNameField.getText(),
                                          gold,
                                          currentEnemyID,
                                          life,
                                          speed,
                                          skill);
                        myEnemyDesignTab.addEnemy(myImageSource, myNameField.getText());
                        myNameField.setText("");
                        myGoldField.setText("");
                        myLifeField.setText("");
                        mySpeedField.setText("");
                        clearAnimationPanel();
                    }
                }
                catch (NumberFormatException n)
                {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }
            }
        };
        return listener;
    }
}
