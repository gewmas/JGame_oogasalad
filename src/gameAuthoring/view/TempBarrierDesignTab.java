package gameAuthoring.view;

import gameAuthoring.JSONObjects.TemporaryBarrierJSONObject;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;


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

    public void addCreatedBarriers () {
        myCreatedTempBarriers = new JScrollPane(myScrollPanel);
        myCreatedTempBarriers.getViewport().setOpaque(false);
        myCreatedTempBarriers.setOpaque(false);
        myCreatedTempBarriers.setPreferredSize(new Dimension(380, 350));
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myCreatedTempBarriers.setBorder(BorderFactory
                .createTitledBorder(b, "Created Waves",
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    new Font("Calibri", Font.PLAIN, 20)));
    }

    public void addBarrierCreationButton () {
        JButton createBarrierButton = new JButton("Create New Barrier");
        createBarrierButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        createBarrierButton.addMouseListener(createBarrierListener());
        myContentPanel.add(createBarrierButton);
    }

    public void addBarrierName () {
        JLabel name = new JLabel("Name");
        name.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myNameField = new JTextField();
        myNameField.setPreferredSize(new Dimension(200, 30));
        myNameField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(name);
        myContentPanel.add(myNameField);
    }

    public void addBarrierDamage () {
        JLabel damage = new JLabel("Damage");
        damage.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(new Dimension(200, 30));
        myDamageField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(damage);
        myContentPanel.add(myDamageField);
    }

    public void addBarrierCost () {
        JLabel cost = new JLabel("Cost");
        cost.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myCostField = new JTextField();
        myCostField.setPreferredSize(new Dimension(200, 30));
        myCostField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(cost);
        myContentPanel.add(myCostField);
    }

    public void addBarrierExpiry () {
        JLabel expire = new JLabel("Expire");
        expire.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myExpiryField = new JTextField();
        myExpiryField.setPreferredSize(new Dimension(200, 30));
        myExpiryField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(expire);
        myContentPanel.add(myExpiryField);
    }

    public void addBarrierDescription () {
        JLabel description = new JLabel("Description");
        description.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myDescriptionField = new JTextArea(2, 15);
        myDescriptionField.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(myDescriptionField);
        myContentPanel.add(description);
        myContentPanel.add(scrollPane);
    }

    public void addBarrierCreationImage () {
        JLabel image = new JLabel("Choose image");
        image.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myBarrierImage = new ImageLabel();
        myContentPanel.add(image);
        myContentPanel.add(myBarrierImage);
    }

    protected void addBarrier (File imgSource, String BarrierName) {
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

    public MouseAdapter createBarrierListener () {
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
                    JOptionPane.showMessageDialog(null, "One or more fields invalid!");
                }
            }
        };

        return listener;
    }
}
