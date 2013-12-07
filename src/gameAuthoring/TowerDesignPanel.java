package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.miginfocom.swing.MigLayout;


public class TowerDesignPanel extends JPanel {

    Map<String, JPanel> TowerPanelMap;

    private TowerDesignTab myTowerDesignTab;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/img");

    JComboBox<String> myTypeOptions;
    private JTextField myNameField;
    private JTextField myDamageField;
    private JTextField myAttackSpeedField;
    private JComboBox<Integer> myAttackModeOptions;
    private JTextField myRangeField;
    private JTextField myCostField;
    private JTextField myRecyclePriceField;
    private JTextArea myDescriptionField;
    private JButton createTowerButton;
    private JScrollPane scrollPane;
    
    private JPanel myDataPanel;

    private File myImageSource;

    private JLabel myTowerImage;

    private final static String[] TOWER_TYPES = { "DefaultTower", "MultipleShootingTower",
                                                 "BoostTower", "MagicTower" };
    private final static Integer[] ATTACK_MODES = { 1, 2 };
    private final static String[] MAGIC_TYPES = { "FrozeMagic", "SpeedUpMagic", "BoostMagic"};
    

    JLabel name, damage, attackSpeed, attackMode, range, cost, recyclePrice, description, towerImageChooser;

    public TowerDesignPanel (TowerDesignTab towerDesignTab) {
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter(
                                            "JPG & GIF Images", "jpg", "gif", "png");
        INPUT_CHOOSER.setFileFilter(filter);
        myTowerDesignTab = towerDesignTab;

        initializeComponents();
        
        JLabel type = new JLabel("Type");
        type.setFont(Constants.defaultBodyFont);

        JComboBox<String> myTypeOptions = new JComboBox<String>(TOWER_TYPES);
        myTypeOptions.setFont(Constants.defaultBodyFont);
        myTypeOptions.addActionListener(createTowerTypeListener());

        this.setLayout(new MigLayout("wrap 2"));
        this.add(type);
        this.add(myTypeOptions);

        this.setLayout(new MigLayout("wrap 1"));
        JPanel myDataPanel = createTowerPanel();
        myDataPanel.add(createTowerButton);
        this.add(myDataPanel);

        Border b = BorderFactory.createLineBorder(Color.black, 1);
        this.setBorder(b);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(380, 500));
    }

    private ActionListener createTowerTypeListener(){

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                System.out.println("Type changed");  
            }
            
        };
        
        return a;
    
        
    }
    
    // Methods to create new panels
    private JPanel createTowerPanel () {
        JPanel panel = new JPanel();

        panel.setLayout(new MigLayout("wrap 2"));

        // JButton towerImageChooser = new JButton("Choose tower image");
        // towerImageChooser.setFont(Constants.defaultBodyFont);
        // towerImageChooser.addMouseListener(createPathListener());


        panel.add(name);
        panel.add(myNameField);
        panel.add(damage);
        panel.add(myDamageField);
        panel.add(attackSpeed);
        panel.add(myAttackSpeedField);
        panel.add(attackMode);
        panel.add(myAttackModeOptions);
        panel.add(range);
        panel.add(myRangeField);
        panel.add(cost);
        panel.add(myCostField);
        panel.add(recyclePrice);
        panel.add(myRecyclePriceField);
        panel.add(description);
        panel.add(scrollPane);
        panel.add(towerImageChooser);
        panel.add(myTowerImage, "gap 0 0 10 10");
        // panel.add(myTowerImage);
      
        return panel;

    }

    private JPanel createMagicTowerPanel () {
        JPanel panel = createTowerPanel();
        
        JLabel magicFactor = new JLabel("Magic Factor");
        magicFactor.setFont(Constants.defaultBodyFont);
        JLabel magic = new JLabel("Magic");
        magic.setFont(Constants.defaultBodyFont);
        
        JTextField magicFactorField = new JTextField();
        magicFactorField.setPreferredSize(new Dimension(200, 30));
        magicFactorField.setFont(Constants.defaultBodyFont);
        JComboBox<String> magicOptions = new JComboBox<String>(MAGIC_TYPES);
        magicOptions.setFont(Constants.defaultBodyFont);
        
        panel.add(magicFactor);
        panel.add(magicFactorField);
        panel.add(magic);
        panel.add(magicOptions);
        
        return panel;
    }

    private JPanel createBoostTowerPanel () { //To be refactored
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("wrap 2"));
        panel.add(name);
        panel.add(myNameField);
        panel.add(damage);
        panel.add(myDamageField);
        panel.add(attackSpeed);
        panel.add(myAttackSpeedField);
        panel.add(range);
        panel.add(myRangeField);
        panel.add(cost);
        panel.add(myCostField);
        panel.add(recyclePrice);
        panel.add(myRecyclePriceField);
        panel.add(description);
        panel.add(scrollPane);
        panel.add(towerImageChooser);
        panel.add(myTowerImage, "gap 0 0 10 10");
        
        JLabel boostFactor = new JLabel("Boost Factor");
        boostFactor.setFont(Constants.defaultBodyFont);
        JTextField boostFactorField = new JTextField();
        boostFactorField.setPreferredSize(new Dimension(200, 30));
        boostFactorField.setFont(Constants.defaultBodyFont);
        
        panel.add(boostFactor);
        panel.add(boostFactorField);
        return panel;
    }

    private JPanel createMultipleShootingTowerPanel () {
        JPanel panel = createTowerPanel();
        
        JLabel attackAmount = new JLabel("Attack Amount");
        attackAmount.setFont(Constants.defaultBodyFont);
        JTextField attackAmountField = new JTextField();
        attackAmountField.setPreferredSize(new Dimension(200, 30));
        attackAmountField.setFont(Constants.defaultBodyFont);
        
        panel.add(attackAmount);
        panel.add(attackAmountField);
        return panel;
    }

    public MouseAdapter createTowerImageListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                File imgSource = GameAuthoringGUI.mySelectedImage;
                myImageSource = imgSource;
                Image tower;
                try {
                    tower = ImageIO.read(imgSource);
                    tower = tower.getScaledInstance(50, 50, Image.SCALE_FAST);
                    myTowerImage.setIcon(new ImageIcon(tower));
                }
                catch (IOException e1) {
                    e1.printStackTrace();
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
                try
                {
                    String type = TOWER_TYPES[myTypeOptions.getSelectedIndex()];
                    System.out.println(type);
                    String name = myNameField.getText();
                    int damage = Integer.parseInt(myDamageField.getText());
                    int attackSpeed = Integer.parseInt(myAttackSpeedField.getText());
                    int range = Integer.parseInt(myRangeField.getText());
                    int cost = Integer.parseInt(myCostField.getText());
                    int recyclePrice = Integer.parseInt(myRecyclePriceField.getText());
                    String description = myDescriptionField.getText();

                    if (name == null || damage < 0 || attackSpeed < 0 || range < 0 || cost < 0 ||
                        recyclePrice < 0 || description == null) {
                        JOptionPane
                                .showMessageDialog(null,
                                                   "Cannot have negative values for gold, life, or speed");
                    }
                    else {
                        myGameData
                                .addTower(type, name,
                                          myImageSource.toString()
                                                  .replace(System.getProperties()
                                                          .getProperty("user.dir") + "/",
                                                           ""),
                                          damage,
                                          attackSpeed,
                                          1, // attackMode?
                                          range,
                                          cost,
                                          recyclePrice,
                                          description);

                        myTowerDesignTab.addTower(myImageSource, myNameField.getText());
                        myNameField.setText("");
                        myDamageField.setText("");
                        myAttackSpeedField.setText("");
                        myRangeField.setText("");
                        myCostField.setText("");
                        myRecyclePriceField.setText("");
                        myTowerImage.setIcon(null);
                    }
                }
                catch (NumberFormatException n) {
                    JOptionPane
                            .showMessageDialog(null,
                                               "Invalid input");
                }

            }
        };
        return listener;
    }
    
    private void initializeComponents(){
        name = new JLabel("Name");
        name.setFont(Constants.defaultBodyFont);
        damage = new JLabel("Damage");
        damage.setFont(Constants.defaultBodyFont);
        attackSpeed = new JLabel("Attack Speed");
        attackSpeed.setFont(Constants.defaultBodyFont);
        attackMode = new JLabel("Attack Mode");
        attackMode.setFont(Constants.defaultBodyFont);
        range = new JLabel("Range");
        range.setFont(Constants.defaultBodyFont);
        cost = new JLabel("Cost");
        cost.setFont(Constants.defaultBodyFont);
        recyclePrice = new JLabel("Recycle Price");
        recyclePrice.setFont(Constants.defaultBodyFont);
        description = new JLabel("Description");
        description.setFont(Constants.defaultBodyFont);

        myNameField = new JTextField();
        myNameField.setPreferredSize(new Dimension(200, 30));
        myNameField.setFont(Constants.defaultBodyFont);
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(new Dimension(200, 30));
        myDamageField.setFont(Constants.defaultBodyFont);
        myAttackSpeedField = new JTextField();
        myAttackSpeedField.setPreferredSize(new Dimension(200, 30));
        myAttackSpeedField.setFont(Constants.defaultBodyFont);
        myAttackModeOptions = new JComboBox<Integer>(ATTACK_MODES);
        myAttackModeOptions.setFont(Constants.defaultBodyFont);
        myRangeField = new JTextField();
        myRangeField.setPreferredSize(new Dimension(200, 30));
        myRangeField.setFont(Constants.defaultBodyFont);
        myCostField = new JTextField();
        myCostField.setPreferredSize(new Dimension(200, 30));
        myCostField.setFont(Constants.defaultBodyFont);
        myRecyclePriceField = new JTextField();
        myRecyclePriceField.setPreferredSize(new Dimension(200, 30));
        myRecyclePriceField.setFont(Constants.defaultBodyFont);
        myDescriptionField = new JTextArea(3, 18);
        
        towerImageChooser = new JLabel("Choose image");
        towerImageChooser.setFont(Constants.defaultBodyFont);

        myTowerImage = new JLabel();
        myTowerImage.setPreferredSize(new Dimension(50, 50));
        Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
        myTowerImage.setBorder(border);
        myTowerImage.addMouseListener(createTowerImageListener());

        myDescriptionField.setLineWrap(true);
        scrollPane = new JScrollPane(myDescriptionField);

        createTowerButton = new JButton("Create Tower");
        createTowerButton.setFont(Constants.defaultBodyFont);
        createTowerButton.addMouseListener(createTowerButtonListener(this));
        
        
    }
}
