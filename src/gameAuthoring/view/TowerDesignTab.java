package gameAuthoring.view;

import gameAuthoring.JSONObjects.TowerJSONObject;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;


/**
 * @author BecLai
 * 
 */
public class TowerDesignTab extends Tab {

    private JScrollPane myCreatedTowers;
    private JPanel myScrollPanel;
    private JPanel myMainPanel;
    private JPanel myContentPanel;

    private Map<String, JPanel> myTowerPanelMap;
    private JComboBox<String> myTypeOptions;
    private JTextField myNameField;
    private JTextField myDamageField;
    private JTextField myAttackSpeedField;
    private JComboBox<Integer> myAttackModeOptions;
    private JTextField myRangeField;
    private JTextField myCostField;
    private JTextField myRecyclePriceField;
    private JTextArea myDescriptionField;
    private JTextField magicFactorField;
    private JTextField boostFactorField;
    private JTextField attackAmountField;

    private JButton createTowerButton;
    private JScrollPane scrollPane;
    private JComboBox<String> magicOptions;

    private ImageLabel myTowerImage;

    private final static String[] TOWER_TYPES = { "DefaultTower", "MultipleShootingTower",
                                                 "BoostTower", "MagicTower" };
    private final static Integer[] ATTACK_MODES = { 0, 1, 2, 3 };
    private final static String[] MAGIC_TYPES = { "FrozeMagic", "SpeedUpMagic", "BoostMagic" };

    JLabel type, name, damage, attackSpeed, attackMode, range, cost, recyclePrice, description,
            towerImageChooser;

    public TowerDesignTab () {
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        myMainPanel = new GradientPanel(new MigLayout("wrap 2"));
        JLabel title = new JLabel("Tower Design");
        title.setFont(new Font("Calibri", Font.PLAIN, 30));
        title.setForeground(new Color(80, 80, 80));
        myMainPanel.add(title, "span 2");
        myMainPanel.setPreferredSize(new Dimension(500, 500));
        myScrollPanel = new JPanel(new MigLayout("wrap 8"));
        myScrollPanel.setOpaque(false);
        createMainPanel();
        myMainPanel.add(myContentPanel);
        myCreatedTowers = new JScrollPane(myScrollPanel);
        myCreatedTowers.setPreferredSize(new Dimension(380, 500));
        myCreatedTowers.setOpaque(false);
        myCreatedTowers.getViewport().setOpaque(false);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myCreatedTowers.setBorder(BorderFactory
                .createTitledBorder(b, "Created Towers",
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    new Font("Calibri", Font.PLAIN, 20)));
        myMainPanel.add(myCreatedTowers);
        return myMainPanel;
    }

    private void createMainPanel () {
        myContentPanel = new JPanel();
        initializeComponents();

        type = new JLabel("Type");

        type.setFont(Constants.DEFAULT_BODY_FONT);

        myTypeOptions = new JComboBox<String>(TOWER_TYPES);
        myTypeOptions.setFont(Constants.DEFAULT_BODY_FONT);
        myTypeOptions.addActionListener(createTowerTypeListener(myContentPanel));

        myContentPanel.setLayout(new MigLayout("wrap 2"));
        myContentPanel.add(type);
        myContentPanel.add(myTypeOptions);

        myContentPanel.setLayout(new MigLayout("wrap 1"));
        JPanel myDataPanel = createTowerPanel();
        myDataPanel.setOpaque(false);
        myDataPanel.add(createTowerButton);
        myContentPanel.add(myDataPanel);

        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myContentPanel.setBorder(b);
        myContentPanel.setOpaque(false);
        myContentPanel.setPreferredSize(new Dimension(380, 500));
    }

    public void addTower (File imgSource, String towerName) {
        System.out.println(towerName);
        JLabel towerIcon = new JLabel();
        try {
            Image towerImage = ImageIO.read(imgSource);
            towerIcon.setIcon(new ImageIcon(towerImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JLabel towerNameLabel = new JLabel(towerName);
        towerNameLabel.setFont(Constants.DEFAULT_BODY_FONT);
        myScrollPanel.add(towerNameLabel);
        myScrollPanel.add(towerIcon);

    }

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub

    }

    private ActionListener createTowerTypeListener (final JPanel contentPanel) {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String towerType = (String) myTypeOptions.getSelectedItem();
                if (towerType != null) {

                    System.out.println(towerType);
                    contentPanel.removeAll();
                    contentPanel.add(type);
                    contentPanel.add(myTypeOptions);

                    JPanel dataPanel;

                    if (towerType.equals("MagicTower")) {
                        System.out.println("magic");
                        dataPanel = createMagicTowerPanel();
                    }

                    else if (towerType.equals("MultipleShootingTower")) {
                        System.out.println("mult");
                        dataPanel = createMultipleShootingTowerPanel();
                    }

                    else if (towerType.equals("BoostTower")) {
                        System.out.println("boost");
                        dataPanel = createBoostTowerPanel();
                    }

                    else {
                        dataPanel = createTowerPanel();
                    }

                    dataPanel.setOpaque(false);
                    dataPanel.add(createTowerButton);
                    dataPanel.revalidate();

                    JScrollPane scrollPane = new JScrollPane(dataPanel);
                    Border empty = BorderFactory.createEmptyBorder();
                    scrollPane.setBorder(empty);
                    scrollPane.getViewport().setOpaque(false);
                    scrollPane.setOpaque(false);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    contentPanel.add(scrollPane);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                    clearTextFields();
                }
            }

        };

        return listener;

    }

    // Methods to create new panels
    private JPanel createTowerPanel () {
        JPanel panel = new JPanel();

        panel.setLayout(new MigLayout("wrap 2"));

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
        magicFactor.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel magic = new JLabel("Magic");
        magic.setFont(Constants.DEFAULT_BODY_FONT);

        magicFactorField = new JTextField();
        magicFactorField.setPreferredSize(new Dimension(150, 30));
        magicFactorField.setFont(Constants.DEFAULT_BODY_FONT);
        magicOptions = new JComboBox<String>(MAGIC_TYPES);
        magicOptions.setFont(Constants.DEFAULT_BODY_FONT);

        panel.add(magicFactor);
        panel.add(magicFactorField);
        panel.add(magic);
        panel.add(magicOptions);

        return panel;
    }

    private JPanel createBoostTowerPanel () { // To be refactored
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
        boostFactor.setFont(Constants.DEFAULT_BODY_FONT);
        boostFactorField = new JTextField();
        boostFactorField.setPreferredSize(new Dimension(200, 30));
        boostFactorField.setFont(Constants.DEFAULT_BODY_FONT);

        panel.add(boostFactor);
        panel.add(boostFactorField);
        return panel;
    }

    private JPanel createMultipleShootingTowerPanel () {
        JPanel panel = createTowerPanel();

        JLabel attackAmount = new JLabel("Attack Amount");
        attackAmount.setFont(Constants.DEFAULT_BODY_FONT);
        attackAmountField = new JTextField();
        attackAmountField.setPreferredSize(new Dimension(200, 30));
        attackAmountField.setFont(Constants.DEFAULT_BODY_FONT);

        panel.add(attackAmount);
        panel.add(attackAmountField);

        return panel;
    }

    public MouseAdapter createTowerButtonListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {

                try
                {
                    String type = TOWER_TYPES[myTypeOptions.getSelectedIndex()];
  
                    // Things that all towers have in common
                    String name = myNameField.getText();
                    int damage = Integer.parseInt(myDamageField.getText());
                    double attackSpeed = Double.parseDouble(myAttackSpeedField.getText());
                    int range = Integer.parseInt(myRangeField.getText());
                    int cost = Integer.parseInt(myCostField.getText());
                    int recyclePrice = Integer.parseInt(myRecyclePriceField.getText());
                    String description = myDescriptionField.getText();
                    String imageID = myTowerImage.getID();

                    if (name == null || damage < 0 || attackSpeed < 0 || range < 0 || cost < 0 ||
                        recyclePrice < 0 || description == null) {
                        JOptionPane
                                .showMessageDialog(null,
                                                   "Cannot have negative values for gold, life, or speed");
                    }

                    TowerJSONObject tower;
                    if (type.equals("DefaultTower")) {
                        int mode = myAttackModeOptions.getSelectedIndex();

                        tower = new TowerJSONObject(type,
                                                    name,
                                                    imageID,
                                                    damage,
                                                    attackSpeed,
                                                    mode, // attackMode?
                                                    range,
                                                    cost,
                                                    recyclePrice,
                                                    description);

                    }

                    else if (type.equals("MagicTower")) {
                        int mode = myAttackModeOptions.getSelectedIndex();
                        String magicType = (String) magicOptions.getSelectedItem();
                        double magicFactor = Double.parseDouble(magicFactorField.getText());

                        tower = new TowerJSONObject(type, name, imageID, damage,
                                                    attackSpeed, mode, range, cost, recyclePrice,
                                                    description, magicFactor, magicType);
                    }

                    else if (type.equals("BoostTower")) {
                        double boostFactor = Double.parseDouble(boostFactorField.getText());

                        tower = new TowerJSONObject(type,
                                                    name,
                                                    imageID,
                                                    damage,
                                                    attackSpeed,
                                                    range,
                                                    cost,
                                                    recyclePrice,
                                                    description,
                                                    boostFactor);

                    }

                    else {
                        int mode = myAttackModeOptions.getSelectedIndex();
                        int attackAmount = Integer.parseInt(attackAmountField.getText());
                        tower = new TowerJSONObject(type, name, imageID,
                                                    damage, attackSpeed, mode,
                                                    attackAmount, range, cost,
                                                    recyclePrice, description);

                    }

                    setChanged();
                    notifyObservers(tower);
                    addTower(myTowerImage.getImageFile(), myNameField.getText());
                    clearChanged();
                    clearTextFields();

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

    private void clearTextFields () {
        myNameField.setText("");
        myDamageField.setText("");
        myAttackSpeedField.setText("");
        myRangeField.setText("");
        myCostField.setText("");
        myRecyclePriceField.setText("");
        myTowerImage.setIcon(null);
        myDescriptionField.setText("");
        magicFactorField.setText("");
        boostFactorField.setText("");
        attackAmountField.setText("");
    }

    private void initializeComponents () {
        name = new JLabel("Name");
        name.setFont(Constants.DEFAULT_BODY_FONT);
        damage = new JLabel("Damage");
        damage.setFont(Constants.DEFAULT_BODY_FONT);
        attackSpeed = new JLabel("Attack Speed");
        attackSpeed.setFont(Constants.DEFAULT_BODY_FONT);
        attackMode = new JLabel("Attack Mode");
        attackMode.setFont(Constants.DEFAULT_BODY_FONT);
        range = new JLabel("Range");
        range.setFont(Constants.DEFAULT_BODY_FONT);
        cost = new JLabel("Cost");
        cost.setFont(Constants.DEFAULT_BODY_FONT);
        recyclePrice = new JLabel("Recycle Price");
        recyclePrice.setFont(Constants.DEFAULT_BODY_FONT);
        description = new JLabel("Description");
        description.setFont(Constants.DEFAULT_BODY_FONT);

        myNameField = new JTextField();
        myNameField.setPreferredSize(new Dimension(150, 30));
        myNameField.setFont(Constants.DEFAULT_BODY_FONT);
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(new Dimension(150, 30));
        myDamageField.setFont(Constants.DEFAULT_BODY_FONT);
        myAttackSpeedField = new JTextField();
        myAttackSpeedField.setPreferredSize(new Dimension(150, 30));
        myAttackSpeedField.setFont(Constants.DEFAULT_BODY_FONT);
        myAttackModeOptions = new JComboBox<Integer>(ATTACK_MODES);
        myAttackModeOptions.setFont(Constants.DEFAULT_BODY_FONT);
        myRangeField = new JTextField();
        myRangeField.setPreferredSize(new Dimension(150, 30));
        myRangeField.setFont(Constants.DEFAULT_BODY_FONT);
        myCostField = new JTextField();
        myCostField.setPreferredSize(new Dimension(150, 30));
        myCostField.setFont(Constants.DEFAULT_BODY_FONT);
        myRecyclePriceField = new JTextField();
        myRecyclePriceField.setPreferredSize(new Dimension(150, 30));
        myRecyclePriceField.setFont(Constants.DEFAULT_BODY_FONT);
        myDescriptionField = new JTextArea();
        myDescriptionField.setLineWrap(true);
        myDescriptionField.setPreferredSize(new Dimension(150, 30));
        scrollPane = new JScrollPane(myDescriptionField);

        towerImageChooser = new JLabel("Choose Image");
        towerImageChooser.setFont(Constants.DEFAULT_BODY_FONT);

        myTowerImage = new ImageLabel();
        myTowerImage.setMutableStatusTrue();
        myTowerImage.setPreferredSize(new Dimension(50, 50));
        Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
        myTowerImage.setBorder(border);

        createTowerButton = new JButton("Create Tower");
        createTowerButton.setFont(Constants.DEFAULT_BODY_FONT);
        createTowerButton.addMouseListener(createTowerButtonListener());

        myTowerPanelMap = new HashMap<String, JPanel>();
        myTowerPanelMap.put("DefaultTower", createTowerPanel());
        myTowerPanelMap.put("MultipleShootingTower", createMultipleShootingTowerPanel());
        myTowerPanelMap.put("BoostTower", createBoostTowerPanel());
        myTowerPanelMap.put("MagicTower", createMagicTowerPanel());

    }
}
