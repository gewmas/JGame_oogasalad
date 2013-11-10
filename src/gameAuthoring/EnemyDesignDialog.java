package gameAuthoring;

import java.awt.Dimension;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


public class EnemyDesignDialog extends JDialog {

    private EnemyDesignTab myEnemyDesignTab;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    private JTextField myNameField;
    private JTextField myGoldField;
    private JTextField myLifeField;
    private JTextField mySpeedField;

    private File myImageSource;

    public EnemyDesignDialog (EnemyDesignTab enemyDesignTab) {
        myEnemyDesignTab = enemyDesignTab;
        JLabel name = new JLabel("Name");
        JLabel gold = new JLabel("Worth in Gold");
        JLabel lives = new JLabel("Number of Lives");
        JLabel speed = new JLabel("Speed");
        JLabel enemyImage = new JLabel("Enemy Image");

        myNameField = new JTextField();
        myNameField.setPreferredSize(new Dimension(200, 30));
        myGoldField = new JTextField();
        myGoldField.setPreferredSize(new Dimension(200, 30));
        myLifeField = new JTextField();
        myLifeField.setPreferredSize(new Dimension(200, 30));
        mySpeedField = new JTextField();
        mySpeedField.setPreferredSize(new Dimension(200, 30));

        JButton enemyImageChooser = new JButton("Choose tower image");
        JButton createEnemyButton = new JButton("Create Enemy");

        this.setTitle("Creating new Enemy");
        this.setLayout(new MigLayout("wrap 2"));
        this.add(name);
        this.add(myNameField);
        this.add(gold);
        this.add(myGoldField);
        this.add(lives);
        this.add(myLifeField);
        this.add(speed);
        this.add(mySpeedField);
        this.add(enemyImage);
        this.add(enemyImageChooser);
        this.add(createEnemyButton);

    }
}
