package gameEngine.view.gameFrame.tools;

import java.awt.Dimension;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;


/**
 * 
 * @author Lalita Maraj
 *         Panel that displays information according to
 *         html formating specified in the DisplayValue data structure.
 *         This Panel takes as input a DisplayValue data structure
 *         and calls the toString method on it to display the information stored
 *         in the DisplayValue object and formats it according to the html
 *         specified in the DisplayValue object
 * 
 * 
 */
@SuppressWarnings("serial")
public class InfoDisplayPanel extends JPanel {

    private static final int DISPLAY_HEIGHT = 150;
    private static final int DISPLAY_WIDTH = 300;

    private JTextPane text;

    /**
     * Constructor
     * 
     * @param panelTitle title to display on GUI
     */
    public InfoDisplayPanel (String panelTitle) {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(panelTitle);
        setBorder(valuePanelBorder);
        addTextBox();
        this.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        this.setVisible(false);
    }

    /**
     * Adds a textbox to the GUI
     */
    private void addTextBox () {
        text = new JTextPane();
        text.setPreferredSize((new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT)));

        JScrollPane listScrollPane = new JScrollPane(text);
        listScrollPane.setPreferredSize(new Dimension(DISPLAY_WIDTH - 10, DISPLAY_HEIGHT));
        add(listScrollPane);
    }

    /**
     * Updates the textbox with the values in the dispalyValues collection
     * 
     * @param displayValues Collection of information to be displayed
     */
    public void updateDisplayInformation (Collection<DisplayValue> displayValues) {
        clearDisplay();
        StringBuilder t = new StringBuilder();
        for (DisplayValue display : displayValues) {
            t.append(display.toString());
        }
        text.setContentType("text/html");
        text.setText(t.toString());
    }

    public void clearDisplay () {
        text.setText("");
    }

}
