package gameAuthoring;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


public class EnemyDesignPanel extends JPanel {

    private EnemyDesignTab myEnemyDesignTab;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    private JTextField myNameField;
    private JTextField myGoldField;
    private JTextField myLifeField;
    private JTextField mySpeedField;

    private JLabel myEnemyImage;
    private File myImageSource;

    public EnemyDesignPanel (EnemyDesignTab enemyDesignTab) {
        myEnemyDesignTab = enemyDesignTab;
        JLabel name = new JLabel("Name");
        JLabel gold = new JLabel("Worth in Gold");
        JLabel lives = new JLabel("Number of Lives");
        JLabel speed = new JLabel("Speed");

        myNameField = new JTextField();
        myNameField.setPreferredSize(new Dimension(200, 30));
        myGoldField = new JTextField();
        myGoldField.setPreferredSize(new Dimension(200, 30));
        myLifeField = new JTextField();
        myLifeField.setPreferredSize(new Dimension(200, 30));
        mySpeedField = new JTextField();
        mySpeedField.setPreferredSize(new Dimension(200, 30));
        myEnemyImage = new JLabel();

        JButton enemyImageChooser = new JButton("Choose enemy image");
        enemyImageChooser.addMouseListener(createPathListener());
        JButton createEnemyButton = new JButton("Create Enemy");
        createEnemyButton.addMouseListener(createEnemyButtonListener(this));

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
        this.add(myEnemyImage);
        this.add(createEnemyButton);
    }

    public MouseAdapter createPathListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    System.out.println(INPUT_CHOOSER.getSelectedFile());
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    myImageSource = imgSource;
                    Image tower;
                    try {
                        tower = ImageIO.read(imgSource);
                        myEnemyImage.setIcon(new ImageIcon(tower));
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return listener;
    }

    public MouseAdapter createEnemyButtonListener (final EnemyDesignPanel enemyDesignDialog) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                myEnemyDesignTab.addEnemy(myImageSource, myNameField.getText());
                myNameField.setText("");
                myGoldField.setText("");
                myLifeField.setText("");
                mySpeedField.setText("");
            }
        };
        return listener;
    }
}
