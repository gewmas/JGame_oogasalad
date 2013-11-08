package gameEngine.view.store;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import gameEngine.view.Panel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class InfoPanel extends Panel {
    private JTextField valueField;
    private JLabel label;

    public InfoPanel () {
        super();

        valueField = new JTextField("None", 7);

        label = new JLabel();

        Font displayFont = new Font("Serif", Font.BOLD, 18);
        valueField.setFont(displayFont);

        setBackground(Color.white);
        Border valuePanelBorder =
                BorderFactory.createTitledBorder("Tower Details");
        setBorder(valuePanelBorder);

        add(label);

    }

    @Override
    public void displayInfo (String displayInfo){
        label.setText(displayInfo);

        
    }

}
