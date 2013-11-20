package gameEngine.view.gameFrame.inputAndDisplay;

import java.util.Map;
import gameEngine.view.gameFrame.store.InfoDisplayPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


/**
 * @author lalitamaraj
 *         A frame that displays dynamically changing information
 *         and accepts user text input and can specify actions
 *         to be performed by text input when user clicks the
 *         submit button.
 *         The text input behavior is defined in the InputSender's submit
 *         method.
 */
public class InputAndDisplayFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String DISPLAY_PANEL_POSITION = "north";
    private static final String INPUT_PANEL_POSITION = "span 2";
    private static final String LAYOUT_WRAP_SETTINGS = "wrap 3";
    private InfoDisplayPanel displayPanel;

    public InputAndDisplayFrame (String displayName, InputSender inputSender) {
        super();

        MigLayout layout = new MigLayout(LAYOUT_WRAP_SETTINGS);
        JPanel main = new JPanel(layout);
        main.add(new InputPanel(inputSender), INPUT_PANEL_POSITION);
        this.displayPanel = new InfoDisplayPanel(displayName,"Cheat code","Behavior");
        main.add(displayPanel, DISPLAY_PANEL_POSITION);
        add(main);
    }

    public void showFrame () {
        pack();
        setVisible(true);
    }

    /**
     * Update method dynamically changes display Panel information
     * 
     * @param information map of key value pairs to be displayed
     *        in display Panel
     */
    public void updateDisplay (Map<String, String> information) {
        displayPanel.displayInformation(information);
    }

}
