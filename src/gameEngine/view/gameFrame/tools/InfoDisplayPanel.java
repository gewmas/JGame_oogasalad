package gameEngine.view.gameFrame.tools;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
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
public class InfoDisplayPanel extends JPanel {

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
        addTextBox();
        this.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        this.setVisible(false);
    }

    /**
     * Initlizes JList contents and adds
     * 
     * @param column2Name name of row
     * @param keyName name of column
     */
    private void addTextBox () {
        text = new JTextPane();
        text.setPreferredSize((new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT)));

        JScrollPane listScrollPane = new JScrollPane(text);
        listScrollPane.setPreferredSize(new Dimension(DISPLAY_WIDTH - 10, DISPLAY_HEIGHT));
        add(listScrollPane);
    }

    public void updateDisplayInformation (Collection<DisplayValue> toDisplay) {
        clearDisplay();
        StringBuilder t = new StringBuilder();
        for (DisplayValue display : toDisplay) {
            t.append(display.toString());
        }
        text.setContentType("text/html");
        text.setText(t.toString());
    }

    public void clearDisplay () {
        text.setText("");
    }

}
