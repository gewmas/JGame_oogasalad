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
        JLabel valueLabel = new JLabel("Last Selection:");
        // valueLabel.setFont(displayFont);
        valueField = new JTextField("None", 7);
        String initialText = "<html>\n" +
                             "Color and font test:\n" +
                             "<ul>\n" +
                             "<li><font color=red>red</font>\n" +
                             "<li><font color=blue>blue</font>\n" +
                             "<li><font color=green>green</font>\n" +
                             "<li><font size=-2>small</font>\n" +
                             "<li><font size=+2>large</font>\n" +
                             "<li><i>italic</i>\n" +
                             "<li><b>bold</b>\n" +
                             "</ul>\n";
        label = new JLabel(initialText) {
            public Dimension getPreferredSize () {
                return new Dimension(200, 200);
            }

            public Dimension getMinimumSize () {
                return new Dimension(200, 200);
            }

            public Dimension getMaximumSize () {
                return new Dimension(200, 200);
            }
        };

        Font displayFont = new Font("Serif", Font.BOLD, 18);
        valueField.setFont(displayFont);
        // setPreferredSize(new Dimension(100,
        // 150));
        setBackground(Color.white);
        Border valuePanelBorder =
                BorderFactory.createTitledBorder("JList Selection");
        setBorder(valuePanelBorder);
        add(valueLabel);
        add(label);

    }

    public void update (String l) {
        label.setText(l);

        // valueField.setText("<html><b><u>T</u>wo</b><br>lines</html>");

    }

}
