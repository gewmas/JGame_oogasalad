package gameAuthoring.view;

import gameAuthoring.JSONObjects.TemporaryBarrierJSONObject;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.Dimension;
import java.awt.Image;
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
 *         TempBarrierDesignTab allows for design of temporary barriers in game. Name, damage, cost,
 *         expiration, description, and image can be set per barrier. TempBarrierDesignTab inherits
 *         from Tab and is thus an Observable. When all fields in the tab have been complete,
 *         TempBarrierDesignTab notifies its observers by sending them a TempBarrierJSONObject.
 */
public class TempBarrierDesignTab extends Tab {

    private JScrollPane myCreatedTempBarriers;
    private JPanel myScrollPanel;
    private JTextField myNameField;
    private JTextField myDamageField;
    private JTextField myCostField;
    private JTextField myExpiryField;
    private JTextArea myDescriptionField;
    private ImageLabel myBarrierImage;
    private static final Dimension PANEL_DIMENSION = new Dimension(380, 350);
    private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(200, 30);
    private static final String SCROLL_PANEL_WRAP_MODE = "wrap 4";

    /**
     * Creates new TempBarrierDesignTab
     */
    public TempBarrierDesignTab () {
    }

    @Override
    public JPanel getTab () {
        myMainPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myMainPanel.setPreferredSize(StyleConstants.DEFAULT_PANEL_SIZE);
        JLabel title = new JLabel("Temporary Barrier Design");
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, StyleConstants.DEFAULT_SPAN_MODE);
        myScrollPanel = new JPanel(new MigLayout(SCROLL_PANEL_WRAP_MODE));
        myScrollPanel.setOpaque(false);
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
        addBarrierCreationButton();
        myMainPanel.add(myContentPanel);
        addCreatedBarriers();
        myMainPanel.add(myCreatedTempBarriers, "aligny center");
        return myMainPanel;
    }

    @Override
    public void loadJSON (Parser p) {
        JSONArray tempBarriers = p.getJSONArray(JSONConstants.TEMP_BARRIER_STRING);
        JSONObject resources = p.getJSONObject(JSONConstants.RESOURCE_STRING);
        JSONArray images = (JSONArray) resources.get(JSONConstants.IMAGE_STRING);
        Map<String, String> imageMap = new HashMap<String, String>();
        for (int i = 0; i < images.length(); i++) {
            JSONObject image = images.getJSONObject(i);
            String id = image.getString(JSONConstants.ID_STRING);
            String url = image.getString(JSONConstants.URL_STRING);
            imageMap.put(id, url);
        }
        for (int i = 0; i < tempBarriers.length(); i++) {
            JSONObject tempBarrier = tempBarriers.getJSONObject(i);
            String name = tempBarrier.getString(JSONConstants.ID_STRING);
            String imageKey = tempBarrier.getString(JSONConstants.IMAGE_STRING);
            File imageFile = new File(GameAuthoringGUI.FILE_PREFIX + imageMap.get(imageKey));
            setChanged();
            notifyObservers(tempBarrier);
            clearChanged();
            addBarrier(imageFile, name);
        }
    }

    /**
     * Adds ScrollPanel to display created barriers
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
     * Adds button that allows for creation of new barrier
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
     * Adds text field to allow user input for barrier name
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
     * Adds text field to allow user input for barrier damage
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
     * Adds text field to allow user input for barrier cost
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
     * Adds text field to allow user input for barrier expiration time
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
     * Adds text area to allow user input for barrier description
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
     * Adds image label for barrier image
     */
    private void addBarrierCreationImage () {
        JLabel image = new JLabel(StyleConstants.resourceBundle.getString("BarrierSelectImage"));
        image.setFont(StyleConstants.DEFAULT_BODY_FONT);
        image.setToolTipText(StyleConstants.resourceBundle.getString("BarrierSelectImageTip"));
        myBarrierImage = new ImageLabel();
        myBarrierImage.setMutableStatusTrue();
        myContentPanel.add(image);
        myContentPanel.add(myBarrierImage);
    }

    /**
     * Adds barrier icon to ScrollPanel once barrier has been created
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
     * Allows for barrier to be created when clicked
     * 
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
