package gameAuthoring.view;

import gameAuthoring.JSONObjects.TowerJSONObject;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
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
 * @author Rebecca Lai & Susan Zhang
 *         TowerDesignTab allows for users to design different types of Towers for a Tower Defense
 *         game. Four types of towers are allowed to be defined: DefaultTower,
 *         MultipleShootingTower,
 *         BoostTower, and MagicTower. Users are able to define parameters such as tower name, the
 *         rate
 *         at which a tower fires, how much a tower costs, etc., as well any special tower specific
 *         attributes.
 * 
 */
public class TowerDesignTab extends Tab {

    private JScrollPane myCreatedTowers;
    private JPanel myCreatedTowersPanel;
    private JComboBox<String> myTypeOptions;
    private JTextField myNameField;
    private JTextField myDamageField;
    private JTextField myAttackSpeedField;
    private JComboBox<Integer> myAttackModeOptions;
    private JTextField myRangeField;
    private JTextField myCostField;
    private JTextField myRecyclePriceField;
    private JTextArea myDescriptionField;
    private JTextField myMagicFactorField;
    private JTextField myBoostFactorField;
    private JTextField myAttackAmountField;
    private JButton myCreateTowerButton;
    private JScrollPane scrollPane;
    private JComboBox<String> myMagicOptions;
    private ImageLabel myTowerImage;
    private final static String[] TOWER_TYPES = { "DefaultTower", "MultipleShootingTower",
                                                 "BoostTower", "MagicTower" };
    private final static Integer[] ATTACK_MODES = { 0, 1, 2, 3 };
    private final static String[] MAGIC_TYPES = { "FrozeMagic", "HasteMagic", "PoisonMagic",
                                                 "HealMagic", "ArmourMagic" };
    private final static Dimension FIELD_DIMENSION = new Dimension(150, 30);
    private final static Dimension MAIN_PANEL_DIMENSION = new Dimension(500, 500);
    private final static Dimension PANEL_DIMENSION = new Dimension(380, 500);
    private static final String DEFAULT_TOWER_NAME = "DefaultTower";
    private static final String MULTIPLE_SHOOTING_TOWER_NAME = "MultipleShootingTower";
    private static final String BOOST_TOWER_NAME = "BoostTower";
    private static final String MAGIC_TOWER_NAME = "MagicTower";
    private static final String TOWER_IMAGE_FORMATTING = "gap 0 0 10 10";
    private static final String CONTENT_PANEL_WRAP_MODE = "wrap 1";
    private final static String CREATED_TOWERS_WRAP_MODE = "wrap 8";
    JLabel type, myNameLabel, myDamageLabel, myAttackSpeedLabel, myAttackModeLabel, myRangeLabel,
            myCostLabel, myRecyclePriceLabel, myDescriptionLabel,
            myTowerImageChooser;

    /**
     * Creates a new TowerDesignTab
     */
    public TowerDesignTab () {
    }

    @Override
    public JPanel getTab () {
        myMainPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myMainPanel.setPreferredSize(MAIN_PANEL_DIMENSION);
        initializeTitle();
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
        initializeContentPanel();
        myMainPanel.add(myContentPanel);
        initializeCreatedTowersPanel();
        myMainPanel.add(myCreatedTowers);
        return myMainPanel;
    }

    /**
     * Creates panel to store created towers
     */
    public void initializeCreatedTowersPanel () {
        myCreatedTowersPanel = new JPanel(new MigLayout(CREATED_TOWERS_WRAP_MODE));
        myCreatedTowersPanel.setOpaque(false);
        myCreatedTowers = new JScrollPane(myCreatedTowersPanel);
        myCreatedTowers.setPreferredSize(PANEL_DIMENSION);
        myCreatedTowers.setOpaque(false);
        myCreatedTowers.getViewport().setOpaque(false);
        myCreatedTowers.setBorder(BorderFactory
                .createTitledBorder(StyleConstants.DEFAULT_PANEL_BORDER,
                                    StyleConstants.resourceBundle.getString("CreatedTowers"),
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    StyleConstants.TITLE_FONT_2));
    }

    /**
     * Initializes panel for user to fill out tower information
     */
    private void initializeContentPanel () {
        myContentPanel.setLayout(new MigLayout(CONTENT_PANEL_WRAP_MODE));
        type = new JLabel("Type");
        type.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myTypeOptions = new JComboBox<String>(TOWER_TYPES);
        myTypeOptions.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myTypeOptions.addActionListener(createTowerTypeListener(myContentPanel));
        myContentPanel.add(type);
        myContentPanel.add(myTypeOptions);
        JPanel myDataPanel = createTowerPanel();
        myDataPanel.setOpaque(false);
        myDataPanel.add(myCreateTowerButton);
        myContentPanel.add(myDataPanel);
        myContentPanel.setBorder(StyleConstants.DEFAULT_PANEL_BORDER);
        myContentPanel.setOpaque(false);
        myContentPanel.setPreferredSize(PANEL_DIMENSION);
    }

    /**
     * Adds tower to myCreatedTowersPanel
     * 
     * @param imgSource Tower image
     * @param towerName Tower name
     */
    public void addTower (File imgSource, String towerName) {
        JLabel towerIcon = new JLabel();
        Image towerImage;
        try {
            towerImage = ImageIO.read(imgSource);
            towerIcon.setIcon(new ImageIcon(towerImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        JLabel towerNameLabel = new JLabel(towerName);
        towerNameLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myCreatedTowersPanel.add(towerNameLabel);
        myCreatedTowersPanel.add(towerIcon);
    }

    @Override
    public void loadJSON (Parser p) {
        JSONArray towers = p.getJSONArray(JSONConstants.TOWER_STRING);
        JSONObject resources = p.getJSONObject(JSONConstants.RESOURCE_STRING);
        JSONArray images = (JSONArray) resources.get(JSONConstants.IMAGE_STRING);
        Map<String, String> imageMap = new HashMap<String, String>();
        for (int i = 0; i < images.length(); i++) {
            JSONObject image = images.getJSONObject(i);
            String id = image.getString(JSONConstants.ID_STRING);
            String url = image.getString(JSONConstants.URL_STRING);
            imageMap.put(id, url);
        }
        for (int i = 0; i < towers.length(); i++) {
            JSONObject tower = towers.getJSONObject(i);
            String name = tower.getString(JSONConstants.NAME_STRING);
            String imageKey = tower.getString(JSONConstants.IMAGE_STRING_2);
            File imageFile = new File(GameAuthoringGUI.FILE_PREFIX + imageMap.get(imageKey));
            setChanged();
            notifyObservers(tower);
            clearChanged();
            addTower(imageFile, name);
        }
    }

    /**
     * Listener to change the JLabels and Fields depending on what type of tower is to be created
     * 
     * @param contentPanel Panel to be modified
     * @return ActionListener that changes what type of tower can be created
     */
    private ActionListener createTowerTypeListener (final JPanel contentPanel) {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String towerType = (String) myTypeOptions.getSelectedItem();
                if (towerType != null) {
                    contentPanel.removeAll();
                    contentPanel.add(type);
                    contentPanel.add(myTypeOptions);
                    JPanel dataPanel;
                    if (towerType.equals(MAGIC_TOWER_NAME)) {
                        dataPanel = createMagicTowerPanel();
                    }
                    else if (towerType.equals(MULTIPLE_SHOOTING_TOWER_NAME)) {
                        dataPanel = createMultipleShootingTowerPanel();
                    }
                    else if (towerType.equals(BOOST_TOWER_NAME)) {
                        dataPanel = createBoostTowerPanel();
                    }
                    else {
                        dataPanel = createTowerPanel();
                    }
                    dataPanel.setOpaque(false);
                    dataPanel.add(myCreateTowerButton);
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

    /**
     * @return Default Tower creation JPanel
     */
    private JPanel createTowerPanel () {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        panel.add(myNameLabel);
        panel.add(myNameField);
        panel.add(myDamageLabel);
        panel.add(myDamageField);
        panel.add(myAttackSpeedLabel);
        panel.add(myAttackSpeedField);
        panel.add(myAttackModeLabel);
        panel.add(myAttackModeOptions);
        panel.add(myRangeLabel);
        panel.add(myRangeField);
        panel.add(myCostLabel);
        panel.add(myCostField);
        panel.add(myRecyclePriceLabel);
        panel.add(myRecyclePriceField);
        panel.add(myDescriptionLabel);
        panel.add(scrollPane);
        panel.add(myTowerImageChooser);
        panel.add(myTowerImage, TOWER_IMAGE_FORMATTING);
        return panel;
    }

    /**
     * @return Magic Tower creation JPanel
     */
    private JPanel createMagicTowerPanel () {
        JPanel panel = createTowerPanel();
        JLabel magicFactor = new JLabel(StyleConstants.resourceBundle.getString("TowerMagic"));
        magicFactor.setFont(StyleConstants.DEFAULT_BODY_FONT);
        JLabel magic = new JLabel("Magic");
        magic.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myMagicFactorField = new JTextField();
        myMagicFactorField.setPreferredSize(FIELD_DIMENSION);
        myMagicFactorField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myMagicFactorField.setToolTipText(StyleConstants.resourceBundle.getString("TowerMagicTip"));
        myMagicOptions = new JComboBox<String>(MAGIC_TYPES);
        myMagicOptions.setFont(StyleConstants.DEFAULT_BODY_FONT);
        panel.add(magicFactor);
        panel.add(myMagicFactorField);
        panel.add(magic);
        panel.add(myMagicOptions);
        return panel;
    }

    /**
     * 
     * @return Boost Tower creation JPanel
     */
    private JPanel createBoostTowerPanel () {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        panel.add(myNameLabel);
        panel.add(myNameField);
        panel.add(myDamageLabel);
        panel.add(myDamageField);
        panel.add(myAttackSpeedLabel);
        panel.add(myAttackSpeedField);
        panel.add(myRangeLabel);
        panel.add(myRangeField);
        panel.add(myCostLabel);
        panel.add(myCostField);
        panel.add(myRecyclePriceLabel);
        panel.add(myRecyclePriceField);
        panel.add(myDescriptionLabel);
        panel.add(scrollPane);
        panel.add(myTowerImageChooser);
        panel.add(myTowerImage, TOWER_IMAGE_FORMATTING);
        JLabel boostFactor = new JLabel(StyleConstants.resourceBundle.getString("TowerBoost"));
        boostFactor.setFont(StyleConstants.DEFAULT_BODY_FONT);
        boostFactor.setToolTipText(StyleConstants.resourceBundle.getString("TowerBoostTip"));
        myBoostFactorField = new JTextField();
        myBoostFactorField.setPreferredSize(FIELD_DIMENSION);
        myBoostFactorField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        panel.add(boostFactor);
        panel.add(myBoostFactorField);
        return panel;
    }

    /**
     * Creates Multiple Shooting Tower Panel
     * 
     * @return Multiple Shooting Tower JPanel
     */
    private JPanel createMultipleShootingTowerPanel () {
        JPanel panel = createTowerPanel();
        JLabel attackAmount = new JLabel(StyleConstants.resourceBundle.getString("TowerAttack"));
        attackAmount.setFont(StyleConstants.DEFAULT_BODY_FONT);
        attackAmount.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerAttackTip"));
        myAttackAmountField = new JTextField();
        myAttackAmountField.setPreferredSize(FIELD_DIMENSION);
        myAttackAmountField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        panel.add(attackAmount);
        panel.add(myAttackAmountField);
        return panel;
    }

    /**
     * @return MouseAdapter that extracts data from fields, notifies observers, and clears fields
     */
    public MouseAdapter createTowerButtonListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                try
                {
                    String type = TOWER_TYPES[myTypeOptions.getSelectedIndex()];
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
                                                   StyleConstants.resourceBundle
                                                           .getString("TowerInvalidSubmission"));
                    }
                    else {
                        TowerJSONObject tower;
                        if (type.equals(DEFAULT_TOWER_NAME)) {
                            int mode = myAttackModeOptions.getSelectedIndex();
                            tower = new TowerJSONObject(type,
                                                        name,
                                                        imageID,
                                                        damage,
                                                        attackSpeed,
                                                        mode,
                                                        range,
                                                        cost,
                                                        recyclePrice,
                                                        description);

                        }
                        else if (type.equals(MAGIC_TOWER_NAME)) {
                            int mode = myAttackModeOptions.getSelectedIndex();
                            String magicType = (String) myMagicOptions.getSelectedItem();
                            double magicFactor = Double.parseDouble(myMagicFactorField.getText());

                            tower =
                                    new TowerJSONObject(type, name, imageID, damage,
                                                        attackSpeed, mode, range, cost,
                                                        recyclePrice,
                                                        description, magicFactor, magicType);
                        }
                        else if (type.equals(BOOST_TOWER_NAME)) {
                            double boostFactor = Double.parseDouble(myBoostFactorField.getText());
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
                            int attackAmount = Integer.parseInt(myAttackAmountField.getText());
                            tower = new TowerJSONObject(type, name, imageID,
                                                        damage, attackSpeed, mode,
                                                        attackAmount, range, cost,
                                                        recyclePrice, description);
                        }
                        addTower(myTowerImage.getImageFile(), name);
                        setChanged();
                        notifyObservers(tower);
                        clearChanged();
                        clearTextFields();
                    }
                }
                catch (NumberFormatException n) {
                    JOptionPane
                            .showMessageDialog(null,
                                               StyleConstants.resourceBundle
                                                       .getString("TowerInvalidInput"));
                }
            }
        };
        return listener;
    }

    /**
     * Clears text fields
     */
    private void clearTextFields () {
        myNameField.setText(StyleConstants.NULL_STRING);
        myDamageField.setText(StyleConstants.NULL_STRING);
        myAttackSpeedField.setText(StyleConstants.NULL_STRING);
        myRangeField.setText(StyleConstants.NULL_STRING);
        myCostField.setText(StyleConstants.NULL_STRING);
        myRecyclePriceField.setText(StyleConstants.NULL_STRING);
        myTowerImage.setIcon(null);
        myDescriptionField.setText(StyleConstants.NULL_STRING);
        myMagicFactorField.setText(StyleConstants.NULL_STRING);
        myBoostFactorField.setText(StyleConstants.NULL_STRING);
        myAttackAmountField.setText(StyleConstants.NULL_STRING);
    }

    /**
     * Adds title to tab
     */
    private void initializeTitle () {
        JLabel title = new JLabel(StyleConstants.resourceBundle
                .getString("TowerTitle"));
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, StyleConstants.DEFAULT_SPAN_MODE);
    }

    /**
     * Adds name label and field
     */
    private void initializeNameField () {
        myNameLabel = new JLabel(StyleConstants.resourceBundle
                .getString("TowerName"));
        myNameLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myNameField = new JTextField();
        myNameField.setPreferredSize(FIELD_DIMENSION);
        myNameField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myNameField.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerNameTip"));
    }

    /**
     * Adds damage label and field
     */
    private void initializeDamageField () {
        myDamageLabel = new JLabel(StyleConstants.resourceBundle
                .getString("TowerDamage"));
        myDamageLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(FIELD_DIMENSION);
        myDamageField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myDamageLabel.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerDamageTip"));
    }

    /**
     * Adds attack speed label and field
     */
    private void initializeAttackSpeedField () {
        myAttackSpeedLabel = new JLabel(StyleConstants.resourceBundle
                .getString("TowerAttackSpeed"));
        myAttackSpeedLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myAttackSpeedLabel.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerAttackSpeedTip"));
        myAttackSpeedField = new JTextField();
        myAttackSpeedField.setPreferredSize(FIELD_DIMENSION);
        myAttackSpeedField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    /**
     * Adds attack mode label and field
     */
    private void initializeAttackModeField () {
        myAttackModeLabel = new JLabel(StyleConstants.resourceBundle
                .getString("TowerAttackMode"));
        myAttackModeLabel.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerAttackModeTip"));
        myAttackModeLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myAttackModeOptions = new JComboBox<Integer>(ATTACK_MODES);
        myAttackModeOptions.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    /**
     * Adds range label and field
     */
    private void initializeRangeField () {
        myRangeLabel = new JLabel(StyleConstants.resourceBundle
                .getString("TowerRange"));
        myRangeLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myRangeLabel.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerRangeTip"));
        myRangeField = new JTextField();
        myRangeField.setPreferredSize(FIELD_DIMENSION);
        myRangeField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    /**
     * Adds cost label and field
     */
    private void initializeCostField () {
        myCostLabel = new JLabel(StyleConstants.resourceBundle
                .getString("TowerCost"));
        myCostLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myCostLabel.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerCostTip"));
        myCostField = new JTextField();
        myCostField.setPreferredSize(FIELD_DIMENSION);
        myCostField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    /**
     * Adds recycle price label and field
     */
    private void initializeRecycleField () {
        myRecyclePriceLabel = new JLabel(StyleConstants.resourceBundle
                .getString("TowerRecyclePrice"));
        myRecyclePriceLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myRecyclePriceLabel.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerRecyclePrice"));
        myRecyclePriceField = new JTextField();
        myRecyclePriceField.setPreferredSize(FIELD_DIMENSION);
        myRecyclePriceField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    /**
     * Adds description label and field
     */
    private void initializeDescriptionField () {
        myDescriptionLabel = new JLabel(StyleConstants.resourceBundle
                .getString("TowerDescription"));
        myDescriptionLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myDescriptionLabel.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerDescriptionTip"));
        myDescriptionField = new JTextArea();
        myDescriptionField.setLineWrap(true);
        myDescriptionField.setPreferredSize(FIELD_DIMENSION);
        scrollPane = new JScrollPane(myDescriptionField);
    }

    /**
     * Initializing tower image chooser
     */
    private void initializeTowerImageChooser () {
        myTowerImageChooser = new JLabel(StyleConstants.resourceBundle
                .getString("TowerImage"));
        myTowerImageChooser.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myTowerImage = new ImageLabel();
        myTowerImage.setMutableStatusTrue();
    }

    /**
     * Initializes button for creating tower.
     */
    private void initializeTowerCreationButton () {
        myCreateTowerButton = new JButton(StyleConstants.resourceBundle
                .getString("TowerCreation"));
        myCreateTowerButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myCreateTowerButton.setToolTipText(StyleConstants.resourceBundle
                .getString("TowerCreationTip"));
        myCreateTowerButton.addMouseListener(createTowerButtonListener());
    }
}
