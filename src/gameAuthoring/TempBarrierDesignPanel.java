package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


public class TempBarrierDesignPanel extends JPanel {

    private JTextField myNameField;
    private JTextField myDamageField;
    private JTextField myCostField;
    private JTextField myExpiryField;
    private JTextArea myDescriptionField;
    private JScrollPane scrollPane;
    private TempBarrierDesignTab myTempBarrierDesignTab;

    public TempBarrierDesignPanel (TempBarrierDesignTab tempBarrierDesignTab) {
        JLabel name = new JLabel("Name");
        myTempBarrierDesignTab = tempBarrierDesignTab;
        // name.setPreferredSize(new Dimension(100, 30));
        name.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel damage = new JLabel("Damage");
        damage.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel cost = new JLabel("Cost");
        cost.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel expire = new JLabel("Expire");
        expire.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel description = new JLabel("Description");
        description.setFont(Constants.DEFAULT_BODY_FONT);

        myNameField = new JTextField();
        myNameField.setPreferredSize(new Dimension(200, 30));
        myNameField.setFont(Constants.DEFAULT_BODY_FONT);
        myDamageField = new JTextField();
        myDamageField.setPreferredSize(new Dimension(200, 30));
        myDamageField.setFont(Constants.DEFAULT_BODY_FONT);
        myCostField = new JTextField();
        myCostField.setPreferredSize(new Dimension(200, 30));
        myCostField.setFont(Constants.DEFAULT_BODY_FONT);
        myExpiryField = new JTextField();
        myExpiryField.setPreferredSize(new Dimension(200, 30));
        myExpiryField.setFont(Constants.DEFAULT_BODY_FONT);

        myDescriptionField = new JTextArea(2, 15);
        myDescriptionField.setLineWrap(true);
        scrollPane = new JScrollPane(myDescriptionField);

        JButton createBarrierButton = new JButton("Create New Barrier");
        createBarrierButton.setFont(Constants.DEFAULT_BODY_FONT);

        this.setLayout(new MigLayout("wrap 2"));
        this.add(name);
        this.add(myNameField);
        this.add(damage);
        this.add(myDamageField);
        this.add(cost);
        this.add(myCostField);
        this.add(expire);
        this.add(myExpiryField);
        this.add(description);
        this.add(scrollPane);

        this.add(createBarrierButton);

        Border b = BorderFactory.createLineBorder(Color.black, 1);
        this.setPreferredSize(new Dimension(380, 350));
        this.setBorder(b);
        this.setOpaque(false);
    }

    public MouseAdapter createBarrierListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                GameData gameData = myTempBarrierDesignTab.getGameData();

            }
        };

        return listener;
    }

}
