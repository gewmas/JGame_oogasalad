package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import java.awt.Color;
import java.awt.Dimension;
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
    private File myImageSource;

    public EnemyDesignPanel (EnemyDesignTab enemyDesignTab) {
        myEnemyDesignTab = enemyDesignTab;
        JLabel name = new JLabel("Name");
        name.setFont(Constants.defaultBodyFont);
        JLabel gold = new JLabel("Worth in Gold");
        gold.setFont(Constants.defaultBodyFont);
        JLabel lives = new JLabel("Number of Lives");
        lives.setFont(Constants.defaultBodyFont);
        JLabel speed = new JLabel("Speed");
        speed.setFont(Constants.defaultBodyFont);

        myNameField = new JTextField();
        myNameField.setFont(Constants.defaultBodyFont);
        myNameField.setPreferredSize(new Dimension(200, 30));

        myGoldField = new JTextField();
        myGoldField.setPreferredSize(new Dimension(200, 30));
        myGoldField.setFont(Constants.defaultBodyFont);

        myLifeField = new JTextField();
        myLifeField.setPreferredSize(new Dimension(200, 30));
        myLifeField.setFont(Constants.defaultBodyFont);

        mySpeedField = new JTextField();
        mySpeedField.setPreferredSize(new Dimension(200, 30));
        mySpeedField.setFont(Constants.defaultBodyFont);

        JButton enemyImageChooser = new JButton("Add sprite");
        enemyImageChooser.setFont(Constants.defaultBodyFont);
        enemyImageChooser.addMouseListener(createNewEnemyIconListener());

        myAnimationPanel = new JPanel();
        myAnimationPanel.setOpaque(false);
        myAnimationPanel.setPreferredSize(new Dimension(200, 100));
        JScrollPane animationScrollPane = new JScrollPane(myAnimationPanel);
        animationScrollPane.setPreferredSize(new Dimension(200, 80));
        animationScrollPane.getViewport().setOpaque(false);
        animationScrollPane.setOpaque(false);
        animationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JButton createEnemyButton = new JButton("Create Enemy");
        createEnemyButton.setFont(Constants.defaultBodyFont);
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
        this.add(enemyImageChooser);
        this.add(animationScrollPane);
        this.add(createEnemyButton);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        this.setPreferredSize(new Dimension(380, 350));
        this.setBorder(b);
        this.setOpaque(false);
    }

    public MouseAdapter createEnemyImageListener (final JLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                File imgSource = GameAuthoringGUI.mySelectedImage;
                myImageSource = imgSource;
                Image enemy;
                try {
                    enemy = ImageIO.read(imgSource);
                    enemy = enemy.getScaledInstance(50, 50, Image.SCALE_FAST);
                    label.setIcon(new ImageIcon(enemy));
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
        return listener;
    }

    public MouseAdapter createNewEnemyIconListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                System.out.println("Clicked");
                JLabel enemyImage = new JLabel();
                enemyImage.setPreferredSize(new Dimension(50, 50));
                enemyImage.addMouseListener(createEnemyImageListener(enemyImage));
                Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
                enemyImage.setBorder(border);
                myAnimationPanel.add(enemyImage);
                myAnimationPanel.validate();
            }
        };
        return listener;
    }

    public MouseAdapter createEnemyButtonListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                GameData myGameData = myEnemyDesignTab.getGameData();
                try {
                    int gold = Integer.parseInt(myGoldField.getText());
                    int life = Integer.parseInt(myLifeField.getText());
                    double speed = Double.parseDouble(mySpeedField.getText());
                    if (gold < 0 || life < 0) {
                        JOptionPane
                                .showMessageDialog(null,
                                                   "Cannot have negative values for gold, life, or speed");
                    }
                    else {
                        myGameData
                                .addEnemy(myNameField.getText(),
                                          gold,
                                          myImageSource.toString()
                                                  .replace(System.getProperties()
                                                          .getProperty("user.dir") + "/",
                                                           ""),
                                          life,
                                          speed);
                        myEnemyDesignTab.addEnemy(myImageSource, myNameField.getText());
                        myNameField.setText("");
                        myGoldField.setText("");
                        myLifeField.setText("");
                        mySpeedField.setText("");
                        myEnemyImage.setIcon(null);
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
