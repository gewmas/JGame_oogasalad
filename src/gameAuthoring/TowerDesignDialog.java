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
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.miginfocom.swing.MigLayout;


public class TowerDesignDialog extends JDialog {

    private TowerDesignTab myTowerDesignTab;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    private JTextField myNameField;
    private JTextField myDamageField;
    private JTextField myAttackSpeedField;
    private JTextField myRangeField;
    private JTextField myCostField;
    private JTextField myRecyclePriceField;

    private File myImageSource;

    private JLabel myTowerImage;

    public TowerDesignDialog (TowerDesignTab towerDesignTab) {
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter(
                                            "JPG & GIF Images", "jpg", "gif");
        INPUT_CHOOSER.setFileFilter(filter);

        myTowerDesignTab = towerDesignTab;
        JLabel name = new JLabel("Name");
        JLabel damage = new JLabel("Damage");
        JLabel attackspeed = new JLabel("Attack Speed");
        JLabel range = new JLabel("Range");
        JLabel cost = new JLabel("Cost");
        JLabel recyclePrice = new JLabel("Recycle Price");
        myTowerImage = new JLabel();
        JButton towerImageChooser = new JButton("Choose tower image");
        towerImageChooser.addMouseListener(createPathListener());

        myNameField = new JTextField();
        myNameField.setPreferredSize(new Dimension(200, 30));
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(new Dimension(200, 30));
        myAttackSpeedField = new JTextField();
        myAttackSpeedField.setPreferredSize(new Dimension(200, 30));
        myRangeField = new JTextField();
        myRangeField.setPreferredSize(new Dimension(200, 30));
        myCostField = new JTextField();
        myCostField.setPreferredSize(new Dimension(200, 30));
        myRecyclePriceField = new JTextField();
        myRecyclePriceField.setPreferredSize(new Dimension(200, 30));

        JButton createTowerButton = new JButton("Create Tower");
        createTowerButton.addMouseListener(createTowerButtonListener(this));

        this.setTitle("Creating new Tower");
        this.setLayout(new MigLayout("wrap 2"));
        this.add(name);
        this.add(myNameField);
        this.add(damage);
        this.add(myDamageField);
        this.add(attackspeed);
        this.add(myAttackSpeedField);
        this.add(range);
        this.add(myRangeField);
        this.add(cost);
        this.add(myCostField);
        this.add(recyclePrice);
        this.add(myRecyclePriceField);
        this.add(towerImageChooser, "aligny center");
        this.add(myTowerImage);
        this.add(createTowerButton);

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
                        myTowerImage.setIcon(new ImageIcon(tower));
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return listener;
    }

    public MouseAdapter createTowerButtonListener (final TowerDesignDialog towerDesignDialog) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                myTowerDesignTab.addTower(myImageSource, myNameField.getText());
                towerDesignDialog.dispose();
            }
        };
        return listener;
    }

}
