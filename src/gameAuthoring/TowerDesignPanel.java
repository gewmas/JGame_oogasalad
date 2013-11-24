package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.miginfocom.swing.MigLayout;


public class TowerDesignPanel extends JPanel {

    private TowerDesignTab myTowerDesignTab;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/img");

    private JTextField myNameField;
    private JTextField myDamageField;
    private JTextField myAttackSpeedField;
    private JTextField myRangeField;
    private JTextField myCostField;
    private JTextField myRecyclePriceField;

    private File myImageSource;

    private JLabel myTowerImage;

    private String[] towerTypes = { "DefaultTower", "MultipleShootingTower", "BoostTower",
                                   "MagicTower" };

    public TowerDesignPanel (TowerDesignTab towerDesignTab) {
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter(
                                            "JPG & GIF Images", "jpg", "gif", "png");
        INPUT_CHOOSER.setFileFilter(filter);
        myTowerDesignTab = towerDesignTab;

        JLabel type = new JLabel("Type");
        type.setFont(Constants.defaultBodyFont);
        JLabel name = new JLabel("Name");
        name.setFont(Constants.defaultBodyFont);
        JLabel damage = new JLabel("Damage");
        damage.setFont(Constants.defaultBodyFont);
        JLabel attackSpeed = new JLabel("Attack Speed");
        attackSpeed.setFont(Constants.defaultBodyFont);
        JLabel range = new JLabel("Range");
        range.setFont(Constants.defaultBodyFont);
        JLabel cost = new JLabel("Cost");
        cost.setFont(Constants.defaultBodyFont);
        JLabel recyclePrice = new JLabel("Recycle Price");
        recyclePrice.setFont(Constants.defaultBodyFont);
        JLabel description = new JLabel("Description");
        description.setFont(Constants.defaultBodyFont);
        myTowerImage = new JLabel();
        JButton towerImageChooser = new JButton("Choose tower image");
        towerImageChooser.setFont(Constants.defaultBodyFont);
        towerImageChooser.addMouseListener(createPathListener());

        JComboBox<String> myTypeOptions = new JComboBox<String>(towerTypes);
        myTypeOptions.setFont(Constants.defaultBodyFont);
        myNameField = new JTextField();
        myNameField.setPreferredSize(new Dimension(200, 30));
        myNameField.setFont(Constants.defaultBodyFont);
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(new Dimension(200, 30));
        myDamageField.setFont(Constants.defaultBodyFont);
        myAttackSpeedField = new JTextField();
        myAttackSpeedField.setPreferredSize(new Dimension(200, 30));
        myAttackSpeedField.setFont(Constants.defaultBodyFont);
        myRangeField = new JTextField();
        myRangeField.setPreferredSize(new Dimension(200, 30));
        myRangeField.setFont(Constants.defaultBodyFont);
        myCostField = new JTextField();
        myCostField.setPreferredSize(new Dimension(200, 30));
        myCostField.setFont(Constants.defaultBodyFont);
        myRecyclePriceField = new JTextField();
        myRecyclePriceField.setPreferredSize(new Dimension(200, 30));
        myRecyclePriceField.setFont(Constants.defaultBodyFont);

        JTextArea descriptionText = new JTextArea(5, 16);
        descriptionText.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(descriptionText);

        JButton createTowerButton = new JButton("Create Tower");
        createTowerButton.setFont(Constants.defaultBodyFont);
        createTowerButton.addMouseListener(createTowerButtonListener(this));

        this.setLayout(new MigLayout("wrap 2"));
        this.add(type);
        this.add(myTypeOptions);
        this.add(name);
        this.add(myNameField);
        this.add(damage);
        this.add(myDamageField);
        this.add(attackSpeed);
        this.add(myAttackSpeedField);
        this.add(range);
        this.add(myRangeField);
        this.add(cost);
        this.add(myCostField);
        this.add(recyclePrice);
        this.add(myRecyclePriceField);
        this.add(description);
        this.add(scrollPane);
        this.add(towerImageChooser, "aligny center");
        this.add(myTowerImage);
        this.add(createTowerButton);
        Border b = BorderFactory.createLoweredBevelBorder();
        this.setBorder(b);

    }

    public MouseAdapter createPathListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
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

    public MouseAdapter createTowerButtonListener (final TowerDesignPanel towerDesignDialog) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                GameData myGameData = myTowerDesignTab.getGameData();
                // try
                // {
                // int damage = Integer.parseInt(myDamageField.getText());
                // int attackSpeed = Integer.parseInt(myAttackSpeedField.getText());
                // int range = Integer.parseInt(myRangeField.getText());
                // int cost = Integer.parseInt(myCostField.getText());
                // int recyclePrice = Integer.parseInt(myRecyclePriceField.getText());
                // if (damage < 0 || attackSpeed < 0 || range < 0 || cost < 0 || recyclePrice < 0) {
                // JOptionPane
                // .showMessageDialog(null,
                // "Cannot have negative values for gold, life, or speed");
                // }
                // else {
                // myGameData
                // .addTower(myNameField.getText(),
                // myImageSource.toString()
                // .replace(System.getProperties()
                // .getProperty("user.dir") + "/",
                // ""),
                // Integer.parseInt(myDamageField.getText()),
                // Integer.parseInt(myAttackSpeedField.getText()),
                // Integer.parseInt(myRangeField.getText()),
                // Integer.parseInt(myCostField.getText()),
                // Integer.parseInt(myRecyclePriceField.getText()));
                //
                // myTowerDesignTab.addTower(myImageSource, myNameField.getText());
                // myNameField.setText("");
                // myDamageField.setText("");
                // myAttackSpeedField.setText("");
                // myRangeField.setText("");
                // myCostField.setText("");
                // myRecyclePriceField.setText("");
                // myTowerImage.setIcon(null);
                // }
                // }
                // catch (NumberFormatException n) {
                // JOptionPane
                // .showMessageDialog(null,
                // "Invalid input");
                // }
            }
        };
        return listener;
    }
}
