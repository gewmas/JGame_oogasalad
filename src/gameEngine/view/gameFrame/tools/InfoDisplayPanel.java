package gameEngine.view.gameFrame.tools;

import java.awt.Dimension;
import java.util.Map;
import gameEngine.view.Panel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;


/**
 * 
 * @author Lalita Maraj
 *         Panel that displays information as key
 *         value pairs in a table
 * 
 * 
 */
public class InfoDisplayPanel extends Panel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int DISPLAY_HEIGHT = 150;
    private static final int DISPLAY_WIDTH = 300;

    private JTextPane text;

    public InfoDisplayPanel (String name) {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(name);
        setBorder(valuePanelBorder);

        initializeContents("", "");

    }

    public InfoDisplayPanel (String name, String keyName, String valueName) {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(name);
        setBorder(valuePanelBorder);

        initializeContents(keyName, valueName);

    }

    /**
     * Initlizes JList contents and adds
     * 
     * @param column2Name name of row
     * @param keyName name of column
     */
    private void initializeContents (String keyName, String valueName) {
        text = new JTextPane();
        text.setPreferredSize((new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT)));

        JScrollPane listScrollPane = new JScrollPane(text);
        listScrollPane.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        add(listScrollPane);

    }

    /**
     * A method that is used to update the display's information
     * 
     * @param information information to be displayed
     */
    public void displayInformation (Map<String, String> information) {
        System.out.println("Updating information");
        clearDisplay();
        text.setText("");
        StringBuilder t = new StringBuilder();
        for (String key : information.keySet()) {
            t.append("<font color=blue> <b>" + key + ":</b></font>  " + information.get(key) +
                     "<br>");

        }
        text.setContentType("text/html");
        text.setText(t.toString());

    }

    /**
     * A method that is used to update the display's information
     * 
     * @param information information to be displayed
     */
    public void displayInformation (Map<String, String> information, Map<String, String> display) {
        System.out.println("Updating information");
        clearDisplay();
        text.setText("");
        StringBuilder t = new StringBuilder();
        for (String key : display.keySet()) {
            t.append("<font color=blue> <b>" + key + ":</b></font>  <font color=" +
                     display.get(key) + ">" + information.get(key) +
                     "</font><br>");

        }
        text.setContentType("text/html");
        text.setText(t.toString());

    }

    public void displayInfo (String message) {

    }

    public void clearDisplay () {
        text.setText("");
    }
}