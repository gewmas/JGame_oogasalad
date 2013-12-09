package gameAuthoring.view;

import gameAuthoring.JSONObjects.TemporaryBarrierJSONObject;
import gameAuthoring.modifiedSwingComponents.GradientPanel;
import gameEngine.parser.Parser;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca Lai & Susan Zhang
 *         Tab that allows for design of temporary barriers in game
 *         Name, damage, cost, expiration, description, and image can be set per barrier
 */
public class TempBarrierDesignTab extends Tab {

    private JScrollPane myCreatedTempBarriers;
    private JPanel myScrollPanel;
    private JPanel myContentPanel;
    private JTextField myNameField;
    private JTextField myDamageField;
    private JTextField myCostField;
    private JTextField myExpiryField;
    private JTextArea myDescriptionField;
    private ImageLabel myBarrierImage;
    private static final Dimension PANEL_DIMENSION = new Dimension(380, 350);
    private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(200, 30);
    private static final String SCROLL_PANEL_WRAP_MODE = "wrap 4";

    public TempBarrierDesignTab () {
    }

    @Override
    public JPanel getTab () {
        JPanel mainPanel = new GradientPanel(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        mainPanel.setPreferredSize(StyleConstants.DEFAULT_PANEL_SIZE);
        JLabel title = new JLabel("Temporary Barrier Design");
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        mainPanel.add(title, StyleConstants.DEFAULT_SPAN_MODE);
        myScrollPanel = new JPanel(new MigLayout(SCROLL_PANEL_WRAP_MODE));
        myScrollPanel.setOpaque(false);
        myContentPanel = new JPanel();
        myContentPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myContentPanel.setPreferredSize(PANEL_DIMENSION);
        myContentPanel.setBorder(StyleConstants.DEFAULT_PANEL_BORDER);
        myContentPanel.setOpaque(false);
        addBarrierName();
        addBarrierDamage();
        addBarrierCost();
        addBarrierExpiry();
        addBarrierDescription();
        addBarrierCreationImage();
        mainPanel.add(myContentPanel);
        addCreatedBarriers();
        mainPanel.add(myCreatedTempBarriers, "aligny center");
        return mainPanel;
    }

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub

    }

    /**
     * Add ScrollPanel to display created barriers
     */
    private void addCreatedBarriers () {
        myCreatedTempBarriers = new JScrollPane(myScrollPanel);
        myCreatedTempBarriers.getViewport().setOpaque(false);
        myCreatedTempBarriers.setOpaque(false);
        myCreatedTempBarriers.setPreferredSize(PANEL_DIMENSION);
        myCreatedTempBarriers.setBorder(BorderFactory
                .createTitledBorder(StyleConstants.DEFAULT_PANEL_BORDER,
                                    StyleConstants.resourceBundle.getString("CreatedBarriers"),
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    StyleConstants.TITLE_FONT_2));
    }

    /**
     * Add button that allows for creation of new barrier
     */
    private void addBarrierCreationButton () {
        JButton createBarrierButton =
                new JButton(StyleConstants.resourceBundle.getString("NewBarrier"));
        createBarrierButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        createBarrierButton.addMouseListener(createBarrierListener());
        createBarrierButton
                .setToolTipText(StyleConstants.resourceBundle.getString("NewBarrierTip"));
        myContentPanel.add(createBarrierButton);
    }

    /**
     * Add text field to allow user input for barrier name
     */
    private void addBarrierName () {
        JLabel name = new JLabel(StyleConstants.resourceBundle.getString("BarrierName"));
        name.setFont(StyleConstants.DEFAULT_BODY_FONT);
        name.setToolTipText(StyleConstants.resourceBundle.getString("BarrierNameTip"));
        myNameField = new JTextField();
        myNameField.setPreferredSize(TEXT_FIELD_DIMENSION);
        myNameField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(name);
        myContentPanel.add(myNameField);
    }

    /**
     * Add text field to allow user input for barrier damage
     */
    private void addBarrierDamage () {
        JLabel damage = new JLabel(StyleConstants.resourceBundle.getString("BarrierDamage"));
        damage.setFont(StyleConstants.DEFAULT_BODY_FONT);
        damage.setToolTipText(StyleConstants.resourceBundle.getString("BarrierDamageTip"));
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(TEXT_FIELD_DIMENSION);
        myDamageField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(damage);
        myContentPanel.add(myDamageField);
    }

    /**
     * Add text field to allow user input for barrier cost
     */
    private void addBarrierCost () {
        JLabel cost = new JLabel(StyleConstants.resourceBundle.getString("BarrierCost"));
        cost.setFont(StyleConstants.DEFAULT_BODY_FONT);
        cost.setToolTipText(StyleConstants.resourceBundle.getString("BarrierCostTip"));
        myCostField = new JTextField();
        myCostField.setPreferredSize(TEXT_FIELD_DIMENSION);
        myCostField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(cost);
        myContentPanel.add(myCostField);
    }

    /**
     * Add text field to allow user input for barrier expiration time
     */
    private void addBarrierExpiry () {
        JLabel expire = new JLabel(StyleConstants.resourceBundle.getString("BarrierExpiry"));
        expire.setFont(StyleConstants.DEFAULT_BODY_FONT);
        expire.setToolTipText(StyleConstants.resourceBundle.getString("BarrierExpiryTip"));
        myExpiryField = new JTextField();
        myExpiryField.setPreferredSize(TEXT_FIELD_DIMENSION);
        myExpiryField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(expire);
        myContentPanel.add(myExpiryField);
    }

    /**
     * Add text area to allow user input for barrier description
     */
    private void addBarrierDescription () {
        JLabel description =
                new JLabel(StyleConstants.resourceBundle.getString("BarrierDescription"));
        description.setFont(StyleConstants.DEFAULT_BODY_FONT);
        description.setToolTipText(StyleConstants.resourceBundle.getString("BarrierExpiryTip"));
        myDescriptionField = new JTextArea(2, 15);
        myDescriptionField.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(myDescriptionField);
        myContentPanel.add(description);
        myContentPanel.add(scrollPane);
    }

    /**
     * Add image label for barrier image
     */
    private void addBarrierCreationImage () {
        JLabel image = new JLabel(StyleConstants.resourceBundle.getString("BarrierSelectImage"));
        image.setFont(StyleConstants.DEFAULT_BODY_FONT);
        image.setToolTipText(StyleConstants.resourceBundle.getString("BarrierSelectImageTip"));
        myBarrierImage = new ImageLabel();
        myContentPanel.add(image);
        myContentPanel.add(myBarrierImage);
    }

    /**
     * Add barrier icon to ScrollPanel once barrier has been created
     * 
     * @param imgSource is image File to be used for barrier
     * @param BarrierName is image path of image
     */
    private void addBarrier (File imgSource, String BarrierName) {
        JLabel barrierIcon = new JLabel();
        try {
            Image barrierImage = ImageIO.read(imgSource);
            barrierIcon.setIcon(new ImageIcon(barrierImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JLabel barrierNameLabel = new JLabel(BarrierName);
        myScrollPanel.add(barrierNameLabel);
        myScrollPanel.add(barrierIcon);
    }

    /**
     * @return MouseAdapter that allows for barrier to be created once fields are completed
     */
    private MouseAdapter createBarrierListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                String name = myNameField.getText();
                int damage = Integer.parseInt(myDamageField.getText());
                int cost = Integer.parseInt(myCostField.getText());
                int expire = Integer.parseInt(myExpiryField.getText());
                String description = myDescriptionField.getText();
                String image = myBarrierImage.getID();
                if (name != null && description != null && image != null && expire > 0) {
                    TemporaryBarrierJSONObject temporaryBarrier =
                            new TemporaryBarrierJSONObject(name, image, damage, cost, expire,
                                                           description);
                    setChanged();
                    notifyObservers(temporaryBarrier);
                    clearChanged();
                    addBarrier(myBarrierImage.getImageFile(), name);
                    myNameField.setText("");
                    myDamageField.setText("");
                    myCostField.setText("");
                    myExpiryField.setText("");
                    myDescriptionField.setText("");
                    myBarrierImage.reset();
                }
                else {
                    JOptionPane.showMessageDialog(null, StyleConstants.resourceBundle
                            .getString("BarrierInvalidSubmission"));
                }
            }
        };
        return listener;
    }
}
