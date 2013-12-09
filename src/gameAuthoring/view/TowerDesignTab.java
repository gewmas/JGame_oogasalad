package gameAuthoring.view;

import gameAuthoring.JSONObjects.TowerJSONObject;
import gameEngine.parser.Parser;
import java.awt.Dimension;
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
    private JPanel createTowersPanel;
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

    
    private final static Dimension FIELD_DIMENSION = new Dimension(150, 30);
    private final static Dimension MAIN_PANEL_DIMENSION = new Dimension(500, 500);
    private final static Dimension PANEL_DIMENSION = new Dimension(380, 500);
    
    JLabel type, name, damage, attackSpeed, attackMode, range, cost, recyclePrice, description,
            towerImageChooser;

    public TowerDesignTab () {
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        myMainPanel = new GradientPanel(new MigLayout("wrap 2"));     
        initializeTitle();
        myMainPanel.setPreferredSize(MAIN_PANEL_DIMENSION);
        initializeNameField();
        initializeDamageField();
        initializeAttackSpeedField();
        initializeAttackModeField();
        initializeRangeField();
        initializeCostField();
        initializeRecycleField();
        initializeDescriptionField();
        initializeTowerImageChooser();
        initializeTowerCreationButton();
        initializeMap();
        initializeContentPanel();
        myMainPanel.add(myContentPanel);
        initializeCreatedTowersPanel();
        myMainPanel.add(myCreatedTowers);
        return myMainPanel;
    }

    public void initializeCreatedTowersPanel () {
        JPanel createdTowersPanel = new JPanel(new MigLayout("wrap 8"));
        createdTowersPanel.setOpaque(false);
        myCreatedTowers = new JScrollPane(createdTowersPanel);
        myCreatedTowers.setPreferredSize(PANEL_DIMENSION);
        myCreatedTowers.setOpaque(false);
        myCreatedTowers.getViewport().setOpaque(false);
        myCreatedTowers.setBorder(BorderFactory
                .createTitledBorder(StyleConstants.DEFAULT_PANEL_BORDER, "Created Towers",
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    StyleConstants.TITLE_FONT_2));
    }

    private void initializeContentPanel () {
        myContentPanel = new JPanel();
        myContentPanel.setLayout(new MigLayout("wrap 1"));
        type = new JLabel("Type");
        type.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myTypeOptions = new JComboBox<String>(TOWER_TYPES);
        myTypeOptions.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myTypeOptions.addActionListener(createTowerTypeListener(myContentPanel));
        myContentPanel.add(type);
        myContentPanel.add(myTypeOptions);
        JPanel myDataPanel = createTowerPanel();
        myDataPanel.setOpaque(false);
        myDataPanel.add(createTowerButton);
        myContentPanel.add(myDataPanel);
        myContentPanel.setBorder(StyleConstants.DEFAULT_PANEL_BORDER);
        myContentPanel.setOpaque(false);
        myContentPanel.setPreferredSize(PANEL_DIMENSION);
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
        towerNameLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        createTowersPanel.add(towerNameLabel);
        createTowersPanel.add(towerIcon);
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
                    contentPanel.removeAll();
                    contentPanel.add(type);
                    contentPanel.add(myTypeOptions);
                    JPanel dataPanel = myTowerPanelMap.get(towerType);
                    // WHAT IS GOING ON HERE??
                    if (towerType.equals("MagicTower")) {
                        dataPanel = createMagicTowerPanel();
                    }

                    else if (towerType.equals("MultipleShootingTower")) {
                        dataPanel = createMultipleShootingTowerPanel();
                    }

                    else if (towerType.equals("BoostTower")) {
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
        magicFactor.setFont(StyleConstants.DEFAULT_BODY_FONT);
        JLabel magic = new JLabel("Magic");
        magic.setFont(StyleConstants.DEFAULT_BODY_FONT);

        magicFactorField = new JTextField();
        magicFactorField.setPreferredSize(FIELD_DIMENSION);
        magicFactorField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        magicOptions = new JComboBox<String>(MAGIC_TYPES);
        magicOptions.setFont(StyleConstants.DEFAULT_BODY_FONT);

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
        boostFactor.setFont(StyleConstants.DEFAULT_BODY_FONT);
        boostFactorField = new JTextField();
        boostFactorField.setPreferredSize(FIELD_DIMENSION);
        boostFactorField.setFont(StyleConstants.DEFAULT_BODY_FONT);

        panel.add(boostFactor);
        panel.add(boostFactorField);
        return panel;
    }

    private JPanel createMultipleShootingTowerPanel () {
        JPanel panel = createTowerPanel();
        JLabel attackAmount = new JLabel("Attack Amount");
        attackAmount.setFont(StyleConstants.DEFAULT_BODY_FONT);
        attackAmountField = new JTextField();
        attackAmountField.setPreferredSize(FIELD_DIMENSION);
        attackAmountField.setFont(StyleConstants.DEFAULT_BODY_FONT);
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
    
    private void initializeTitle(){
        JLabel title = new JLabel(StyleConstants.resourceBundle
                                  .getString("TowerTitle"));
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, "span 2");
    }

    private void initializeNameField () {
        name = new JLabel(StyleConstants.resourceBundle
                          .getString("TowerName"));
        name.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myNameField = new JTextField();
        myNameField.setPreferredSize(FIELD_DIMENSION);
        myNameField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    private void initializeDamageField () {
        damage = new JLabel(StyleConstants.resourceBundle
                            .getString("TowerDamage"));
        damage.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(FIELD_DIMENSION);
        myDamageField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    private void initializeAttackSpeedField () {
        attackSpeed = new JLabel(StyleConstants.resourceBundle
                                 .getString("TowerAttackSpeed"));
        attackSpeed.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myAttackSpeedField = new JTextField();
        myAttackSpeedField.setPreferredSize(FIELD_DIMENSION);
        myAttackSpeedField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    private void initializeAttackModeField () {
        attackMode = new JLabel(StyleConstants.resourceBundle
                                .getString("TowerAttackMode"));
        attackMode.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myAttackModeOptions = new JComboBox<Integer>(ATTACK_MODES);
        myAttackModeOptions.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    private void initializeRangeField () {
        range = new JLabel(StyleConstants.resourceBundle
                           .getString("TowerRange"));
        range.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myRangeField = new JTextField();
        myRangeField.setPreferredSize(FIELD_DIMENSION);
        myRangeField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    private void initializeCostField () {
        cost = new JLabel(StyleConstants.resourceBundle
                          .getString("TowerCost"));
        cost.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myCostField = new JTextField();
        myCostField.setPreferredSize(FIELD_DIMENSION);
        myCostField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    private void initializeRecycleField () {
        recyclePrice = new JLabel(StyleConstants.resourceBundle
                                  .getString("TowerRecyclePrice"));
        recyclePrice.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myRecyclePriceField = new JTextField();
        myRecyclePriceField.setPreferredSize(FIELD_DIMENSION);
        myRecyclePriceField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    private void initializeDescriptionField () {
        description = new JLabel(StyleConstants.resourceBundle
                                 .getString("TowerDescription"));
        description.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myDescriptionField = new JTextArea();
        myDescriptionField.setLineWrap(true);
        myDescriptionField.setPreferredSize(FIELD_DIMENSION);
        scrollPane = new JScrollPane(myDescriptionField);
    }

    private void initializeTowerImageChooser () {
        towerImageChooser = new JLabel(StyleConstants.resourceBundle
                                       .getString("TowerImage"));
        towerImageChooser.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myTowerImage = new ImageLabel();
        myTowerImage.setMutableStatusTrue();
    }

    private void initializeTowerCreationButton () {
        createTowerButton = new JButton(StyleConstants.resourceBundle
                                        .getString("TowerCreation"));
        createTowerButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        createTowerButton.addMouseListener(createTowerButtonListener());
    }

    private void initializeMap () {
        myTowerPanelMap = new HashMap<String, JPanel>();
        myTowerPanelMap.put("DefaultTower", createTowerPanel());
        myTowerPanelMap.put("MultipleShootingTower", createMultipleShootingTowerPanel());
        myTowerPanelMap.put("BoostTower", createBoostTowerPanel());
        myTowerPanelMap.put("MagicTower", createMagicTowerPanel());
    }
}
